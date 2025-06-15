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
 * @CreatedOn - 08-Dec-2024 12:38:50 am
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sansys.kudlimathspringbootreact.entities.Donatees;
import com.sansys.kudlimathspringbootreact.models.Contact;
import com.sansys.kudlimathspringbootreact.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;

/**
 * 
 */

@RestController
@RequestMapping("/api/v1")
public class ContactController {

  private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
  
  @Autowired
  private EmailService emailService;
  
  @PostMapping("/contact-us")
  public void contactUs(@Valid @RequestBody Contact contact) throws AddressException, MessagingException {
    LOG.info("/contact-us API is called");
    emailService.sendContactHtmlEmail(contact);
  }
  
  
    @PostMapping("/thank-you") public void contactUs(@Valid @RequestBody Donatees donatees) throws
    AddressException, MessagingException { LOG.info("/contact-us API is called");
    emailService.sendHtmlEmail(donatees); }
   
}
