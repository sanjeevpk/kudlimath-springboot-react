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
 * @CreatedOn - 15-Sept-2023 10:35:36 pm
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.controllers;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sansys.kudlimathspringbootreact.models.PayInstrument;
import com.sansys.kudlimathspringbootreact.services.DonateesService;

/**
 * 
 */

@Controller
@CrossOrigin
@RequestMapping("/api/v1")
public class DonationResponseController {
  
  private static final Logger LOG = LoggerFactory.getLogger(DonationController.class);
  
  @Autowired
  private DonateesService donateesService;
  
  @Value("${app.const.redirectURL}")
  private String redirectURL;
      
  @RequestMapping(value = "/donation/response", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public RedirectView fetchDonationResponse(String encData, ModelAndView model) throws JsonMappingException, JsonProcessingException{
    LOG.info("API /donation/response is called");
    Map<?, ?> response = donateesService.fetchDonationResponse(encData);
    LOG.info("Received the fetchDonationResponse() from service");
    
    return new RedirectView(redirectURL+"/#/donation-response?"
        + "merchantTnxId="+response.get("merchantTnxId")
        + "&bankTnxId="+response.get("bankTnxId")
        + "&merchantTnxDate="+response.get("merchantTnxDate")
        + "&merchantId="+response.get("merchantId") 
        + "&atomTokenId="+response.get("atomTokenId")
        + "&responseStatusCode="+response.get("responseStatusCode")
        + "&responseStatusMessage="+response.get("responseStatusMessage")
        + "&responseStatusDescription="+response.get("responseStatusDescription")); 
  }
  
}
