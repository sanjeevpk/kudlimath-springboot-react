/**
 * © Copyright SanSys Pvt. Ltd. All rights reserved. SanSys is a registered trademark and the SanSys graphic logo is a trademark of SanSys Pvt. Ltd.
 * SanSys reserves all the right for this source code. You should not modify or reuse without the noticing it to SanSys. And need to provide 
 * credits where applicable. Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific governing permissions and
 * limitations under the License.
 *
 * @author - Sanjeev
 * @version - 1.0
 * @CreatedOn - 17-Aug-2023 11:26:49 pm
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.naming.directory.InvalidAttributesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.sansys.kudlimathspringbootreact.entities.Donatees;
import com.sansys.kudlimathspringbootreact.models.DonateesModel;
import com.sansys.kudlimathspringbootreact.models.ResponseParser;
import com.sansys.kudlimathspringbootreact.pojos.CustDetails;
import com.sansys.kudlimathspringbootreact.pojos.Extras;
import com.sansys.kudlimathspringbootreact.pojos.HeadDetails;
import com.sansys.kudlimathspringbootreact.pojos.MerchDetails;
import com.sansys.kudlimathspringbootreact.pojos.OtsTransaction;
import com.sansys.kudlimathspringbootreact.pojos.PayDetails;
import com.sansys.kudlimathspringbootreact.pojos.PayInstrument;
import com.sansys.kudlimathspringbootreact.pojos.ServerResponse;
import com.sansys.kudlimathspringbootreact.repositories.DonateesRepository;
import com.sansys.kudlimathspringbootreact.utils.AtomSignature;
import com.sansys.kudlimathspringbootreact.utils.AuthEncryptionAndDecryption;

/**
 * 
 */

@Service
public class DonateesService {
  
  private static final Logger LOG = LoggerFactory.getLogger(DonateesService.class);
  
  @Value("${app.const.merchantId}")
  private String merchantId;
  
  @Value("${app.const.returnURL}")
  private String returnURL;
  
  @Value("${app.const.authEncryptionKey}")
  private String authEncryptionKey;
  
  @Value("${app.const.authDecryptionKey}")
  private String authDecryptionKey;
  
  @Value("${app.const.signatureRequestHashKey}")
  private String signatureRequestHashKey;
  
  @Autowired
  private DonateesRepository donateesRepository;
   
  /**
   * @param donate
   * @return
   * @throws InvalidAttributesException 
   */
  public DonateesModel donate(DonateesModel donateModel) throws InvalidAttributesException {
    Donatees donatees = new Donatees();
    
    if(donateModel != null) {
      extractModelToEntity(donateModel, donatees);
            
      try {
        donatees = donateesRepository.save(donatees);
        
        LOG.info("Donates info ==> {}, {}, {}, {}, {}",
            donatees.getId(),
            donatees.getName(), 
            donatees.getMobile(),
            donatees.getEmail(),
            donatees.getAmount(),
            donatees.getDateTime());
        
        if(donatees != null) {
          
          extractEntityToModel(donateModel, donatees);
          donateModel = processPaymentData(donateModel);
          donateModel = updateTheAtomTokenIdToDB(donateModel);
                   
        }
        
      } catch (Exception e) {
        LOG.error("Error inside donate service method", e);
        e.printStackTrace();
      }
      
    } else {
      throw new InvalidAttributesException("Mandatory field data is missing from the request! Please pass the correct values!");
    }
    
    return donateModel;
  }


  /**
   * @param donateModel
   * @return
   */
  private DonateesModel updateTheAtomTokenIdToDB(DonateesModel donateModel) {
    Optional<Donatees> donatees = donateesRepository.findById(donateModel.getId());
    Donatees donateesEntity = donatees.get();
    donateesEntity.setAtomTokenId(donateModel.getAtomTokenId());
    donateesEntity.setMerchantId(merchantId);
    donateesEntity.setMerchantTnxId(donateModel.getMerchantTnxId());
    donateesEntity.setLastUpdatedDateTime(LocalDateTime.now());
    donateesEntity.setStatus("PAYMENT_INITIATED");
    donateesEntity = donateesRepository.save(donateesEntity);
    //returnUrl just read from property and set in the model response
    donateModel.setReturnUrl(returnURL);
    LOG.info("Updated the payment status");
    return donateModel;
  }

  /**
   * @param donateModel
   * @param donatees
   */
  private DonateesModel extractEntityToModel(DonateesModel donateModel, Donatees donatees) {
    
    donateModel.setId(donatees.getId());
    donateModel.setName(donatees.getName());
    donateModel.setEmail(donatees.getEmail());
    donateModel.setMobile(donatees.getMobile());
    donateModel.setAmount(donatees.getAmount());
    donateModel.setDateTime(donatees.getDateTime());
    
    return donateModel;
  }

  /**
   * @param donateModel
   * @return
   */
  private DonateesModel processPaymentData(DonateesModel donateModel) {
    String encryptedData = "";
    String decryptedData = "";

    System.out.println("############################ PayController Post #########################");
    
    LOG.info("Merchant Id = {}", merchantId);

    String merchantTxnId = UUID.randomUUID().toString();

    System.out.println("MerchantId------: " + merchantId);
    System.out.println("Amount----------: " + donateModel.getAmount());
    System.out.println("MerchantTxnId---: " + merchantTxnId);
    System.out.println("ReturnURL-------: " + returnURL);

    MerchDetails merchDetails = new MerchDetails();
    merchDetails.setMerchId(merchantId);
    merchDetails.setMerchTxnId(merchantTxnId);
    merchDetails.setUserId("");//Not to map
    merchDetails.setPassword("320bda5e");
    DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime date = LocalDateTime.now();
    String dateFormat = myFormat.format(date);
    merchDetails.setMerchTxnDate(dateFormat);

    PayDetails payDetails = new PayDetails();
    payDetails.setAmount(donateModel.getAmount().toString());
    payDetails.setCustAccNo("213232323");//Any int values
    payDetails.setTxnCurrency("INR");

    payDetails.setProduct("TRUST");
    CustDetails custDetails = new CustDetails();
    custDetails.setCustEmail("sanjeev.pk@gmail.com");
    custDetails.setCustMobile("8217794996");

    HeadDetails headDetails = new HeadDetails();
    headDetails.setApi("AUTH");
    headDetails.setVersion("OTSv1.1");
    headDetails.setPlatform("FLASH");

    Extras extras = new Extras();
    extras.setUdf1("");
    extras.setUdf2("");
    extras.setUdf3("");
    extras.setUdf4("");
    extras.setUdf5("");

    PayInstrument payInstrument = new PayInstrument();

    payInstrument.setMerchDetails(merchDetails);
    payInstrument.setPayDetails(payDetails);
    payInstrument.setCustDetails(custDetails);
    payInstrument.setHeadDetails(headDetails);
    payInstrument.setExtras(extras);

    OtsTransaction otsTxn = new OtsTransaction();
    otsTxn.setPayInstrument(payInstrument);

    Gson gson = new Gson();
    String json = gson.toJson(otsTxn);
    System.out.println("Final JsonOutput----: " + json);

    String serverResp = "";
    String decryptResponse = "";
    try {
        encryptedData = AuthEncryptionAndDecryption.getAuthEncrypted(json, authEncryptionKey);
        System.out.println("EncryptedData------: " + encryptedData);

        serverResp = AipayService.getAtomTokenId(merchantId, encryptedData);
        System.out.println("serverResp Result------: " + serverResp);
        System.out.println("serverResp Length------: " + serverResp.length());
        System.out.println("serverResp Condition---: " + serverResp.startsWith("merchId"));

        if ((serverResp != null) && (serverResp.startsWith("merchId"))) {
            decryptResponse = serverResp.split("\\&encData=")[1];
            System.out.println("serrResp---: " + decryptResponse);

            decryptedData = AuthEncryptionAndDecryption.getAuthDecrypted(decryptResponse, authDecryptionKey);
            System.out.println("DecryptedData------: " + decryptedData);

            ServerResponse serverResponse = new ServerResponse();
            serverResponse = (ServerResponse) gson.fromJson(decryptedData, ServerResponse.class);

            String atomTokenId = serverResponse.getAtomTokenId();
            System.out.println("serverResponse-----: " + serverResponse);
            System.out.println("TokenId------------: " + atomTokenId);
            
            donateModel.setAtomTokenId(atomTokenId);
            donateModel.setMerchantTnxId(merchDetails.getMerchTxnId());
            donateModel.setMerchantId(merchantId);

        } else {
            LOG.info("Somethin went wrong");
            System.out.println("Something Went Wrong!");
        }
    } catch (Exception e) {
        LOG.error("Error inside processPaymentData()", e);
        e.printStackTrace();
    }
    return donateModel;
  }

  /**
   * @param donateModel
   * @param donatees
   */
  private Donatees extractModelToEntity(DonateesModel donateModel, Donatees donatees) {
   
    donatees.setName(donateModel.getName());
    donatees.setMobile(donateModel.getMobile());
    donatees.setEmail(donateModel.getEmail());
    donatees.setAmount(donateModel.getAmount());
    donatees.setDateTime(LocalDateTime.now());
    return donatees;
  }


  /**
   * @param encryptedData
   * @return
   * @throws JsonProcessingException 
   * @throws JsonMappingException 
   */
  public Map<?, ?> fetchDonationResponse(String encryptedData) throws JsonMappingException, JsonProcessingException {
    LOG.info("Received the server response for donation = {}", encryptedData);
    String decryptedData = AuthEncryptionAndDecryption.getAuthDecrypted(encryptedData, authDecryptionKey);
    LOG.info("Decrypted response data = {}", decryptedData);

//    ObjectMapper objectMapper = new ObjectMapper(); 
//    ResponseParser resp = objectMapper.readValue(decryptedData, ResponseParser.class);
    
    Gson g = new Gson();  
    ResponseParser resp = g.fromJson(decryptedData, ResponseParser.class);  
    
    LOG.info("Transaction response \n" + 
        "MerchantId = {} \n" + 
        "AtomTokenId = {} \n" +
        "MerchantTnxId = {} \n" + 
        "MerchantTnxDate = {}\n" + 
        "BankTnxId = {}\n" +
        "Response Status Code = {} \n" + 
        "Response Status Message = {} \n" +
        "Response Status Description = {}\n", 
        resp.getPayInstrument().getMerchDetails().getMerchId(),
        resp.getPayInstrument().getPayDetails().getAtomTxnId(),
        resp.getPayInstrument().getMerchDetails().getMerchTxnId(),
        resp.getPayInstrument().getMerchDetails().getMerchTxnDate(),
        resp.getPayInstrument().getPayModeSpecificData().getBankDetails().getBankTxnId(),
        resp.getPayInstrument().getResponseDetails().getStatusCode(),
        resp.getPayInstrument().getResponseDetails().getMessage(),
        resp.getPayInstrument().getResponseDetails().getDescription());
    
    Map<String, String> response = new HashMap<String, String>(); 
    response.put("merchantId", resp.getPayInstrument().getMerchDetails().getMerchId()); 
    response.put("atomTokenId", resp.getPayInstrument().getPayDetails().getAtomTxnId()); 
    response.put("merchantTnxId", resp.getPayInstrument().getMerchDetails().getMerchTxnId()); 
    response.put("merchantTnxDate", resp.getPayInstrument().getMerchDetails().getMerchTxnDate()); 
    response.put("bankTnxId", resp.getPayInstrument().getPayModeSpecificData().getBankDetails().getBankTxnId());
    response.put("responseStatusCode", resp.getPayInstrument().getResponseDetails().getStatusCode());
    response.put("responseStatusMessage", resp.getPayInstrument().getResponseDetails().getMessage());
    response.put("responseStatusDescription", resp.getPayInstrument().getResponseDetails().getDescription());
      
    try { 
      
      Donatees entity = donateesRepository.findByMerchantIdAndMerchantTnxId(
          response.get("merchantId"), response.get("merchantTnxId"));
      
      LOG.info("Retrieved entity details are");
      LOG.info("merchantId = {}", entity.getMerchantId());
      LOG.info("atomTokenId = {}", entity.getAtomTokenId());
      LOG.info("merchantTnxId = {}", entity.getMerchantTnxId());
      
      entity.setAtomTokenId(response.get("atomTokenId"));
      entity.setStatus("PAYMENT_SUCCESSFUL"); 
      entity.setBankTnxId(response.get("bankTnxId"));
      entity.setResponseCode(response.get("responseStatusCode"));
      entity.setResponseMessage(response.get("responseStatusMessage"));
      entity.setResponseDescription(response.get("responseStatusDescription"));
      entity.setLastUpdatedDateTime(LocalDateTime.now());
      
      donateesRepository.save(entity); 
      LOG.info("Updated donation payment status to success for merchantId = {}, merchantTnxId = {} ", entity.getMerchantId(), entity.getMerchantTnxId()); 
      
    } catch (Exception e) { 
      LOG.debug("Error while updating payment status for merchantId = {}, merchantTnxId = {} ", response.get("merchantId"), response.get("merchantTnxId"), e); 
      e.printStackTrace(); 
    }
    return response;
  }


  /**
   * @param encryptedData
   * @param model
   * @return
   */
  public Map<?, ?> fetchDummyDonationResponse(String encryptedData, ModelAndView model) {
    LOG.info("Received the server response for donation = {}", encryptedData);
    String decryptedData = AuthEncryptionAndDecryption.getAuthDecrypted(encryptedData, authDecryptionKey);
    LOG.info("Decrypted response data = {}", decryptedData);
    Map<String, String> response = new HashMap<String, String>();
    response.put("response", decryptedData);
    LOG.info("ready for redirect to UI");
    return response;
  }


  /**
   * @param request
   * @return
   */
  public String verifyPaymentStatus(OtsTransaction request) {
    String encryptedData = "";
    String decryptedData = "";
    String serverResp = "";
    String decryptResponse = "";
    
    if(request != null && request.getPayInstrument().getPayDetails()!= null) {
//      if(request.get("merchDetails") != null && request.get("payDetails") != null) {
        
        try {
          encryptedData = AuthEncryptionAndDecryption.getAuthEncrypted(request.toString(), authEncryptionKey);
          System.out.println("EncryptedData------: " + encryptedData);

          serverResp = AipayService.getTransactionStatus(merchantId, encryptedData);
          System.out.println("serverResp Result------: " + serverResp);
          System.out.println("serverResp Length------: " + serverResp.length());
          System.out.println("serverResp Condition---: " + serverResp.startsWith("merchId"));

          if ((serverResp != null) ) {
              decryptResponse = serverResp.split("\\&merchId=")[0].split("=")[1];
              System.out.println("serrResp---: " + decryptResponse);

              decryptedData = AuthEncryptionAndDecryption.getAuthDecrypted(decryptResponse, authDecryptionKey);
              System.out.println("DecryptedData------: " + decryptedData);

//              ServerResponse serverResponse = new ServerResponse();
//              serverResponse = (ServerResponse) gson.fromJson(decryptedData, ServerResponse.class);
//
//              String atomTokenId = serverResponse.getAtomTokenId();
//              System.out.println("serverResponse-----: " + serverResponse);
//              System.out.println("TokenId------------: " + atomTokenId);
          }
        }catch(Exception e) {
          LOG.error("Error occurred verifyPaymentStatus()", e);
        }
      }
//    }
    return decryptedData;
  }


  /**
   * @param request
   * @return
   */
  public Map<String, Object> getSignature(Map<String, Object> request) {
    Map<String, Object> map = new HashMap<String, Object>();
    String input = (String) map.get("merchID") +
        map.get("merchTxnID") + 
        map.get("amount") + 
        map.get("txnCurrency");
    LOG.info("Input for signature = {}", input);
    
    String output = AtomSignature.generateSignature(signatureRequestHashKey, input);
    map.put("signature", output);
    return map;
  }

}
