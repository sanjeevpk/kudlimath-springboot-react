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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sansys.kudlimathspringbootreact.services.DonateesService;

/**
 * 
 */

@Controller
@CrossOrigin
@RequestMapping("/api/v1")
public class DonationResponseController {
  
  @Autowired
  private DonateesService donateesService;
  
  @Value("${app.const.redirectURL}")
  private String redirectURL;
    
//    @RequestMapping(value = "/donation/response", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)
//    public RedirectView fetchDonationResponse(String encData, RedirectAttributes attributes) throws JsonMappingException, JsonProcessingException{
//      Map<?, ?> response = donateesService.fetchDonationResponse(encData);
//      attributes.addAttribute("response", response);
//      return new RedirectView("http://localhost:8080/donation-response");
//    }
  
    @RequestMapping(value = "/donation/response", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public RedirectView fetchDonationResponse(String encData, ModelAndView model) throws JsonMappingException, JsonProcessingException{
      Map<?, ?> response = donateesService.fetchDonationResponse(encData);
      return new RedirectView(redirectURL+"/#/donation-response?"
          + "merchantId="+response.get("merchantId") 
          + "&atomTokenId="+response.get("atomTokenId")
          + "&merchantTnxId="+response.get("merchantTnxId")
          + "&merchantTnxDate="+response.get("merchantTnxDate")
          + "&bankTnxId="+response.get("bankTnxId")
          + "&responseStatusCode="+response.get("responseStatusCode")
          + "&responseStatusMessage="+response.get("responseStatusMessage")
          + "&responseStatusDescription="+response.get("responseStatusDescription")); 
    }
    
}
