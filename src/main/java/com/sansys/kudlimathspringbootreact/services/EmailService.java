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
 * @CreatedOn - 09-Aug-2023 1:31:01 am
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.sansys.kudlimathspringbootreact.models.Contact;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * 
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    public void sendHtmlEmail(Contact contact) throws MessagingException {
      MimeMessage message = mailSender.createMimeMessage();

      message.setFrom(new InternetAddress("incarnationpath@gmail.com"));
      message.setRecipients(MimeMessage.RecipientType.TO, contact.getEmail());
      message.setSubject("Thank you for your generous contribution");

      String htmlContent = "<h2>Shri Kudali Aarya Akshobhya Teerth Matha<h2>"
          + "<p>Dear Haribhakta "+contact.getName()+",</p>"
          
          + "<p>You’re welcome! We are very grateful for your generous donation to "
          + "Shri Kudli Aarya Akshobhya Teerth Math. Your contribution will help us "
          + "to support and inspire to do more sevas.</p>"
          
          + "<p>With your donation, you are making a difference in the lives of many Madhva people. "
          + "You are also helping us to achieve our vision of a world where math is appreciated, "
          + "for its contributions towards society.</p>"
          
          + "<p>We hope you will continue to support us in our mission of spreading Madhva Siddhanta. "
          + "You can also follow us on our social media channels to stay updated on our latest news, "
          + "events, and activities. And don’t forget to check out our website for more math resources and opportunities.</p>"
          
          + "<p>Thank you again for your kindness and generosity. We hope to hear from you soon. 😊</p>"
          
          + "<p>Sincerely,</p>"
          
          + "<p>Shri Kudli Aarya Akshobhya Teerth Math</p>"
          + "<a href=\"https://www.srikudliaryaakshobhyateerthmath.org\" target=\"_blank\" rel=\"noopener noreferrer\">www.srikudliaryaakshobhyateerthmath.org</a> "
          + "<p>Get in touch with us via below social links</p>\r\n"
          + "<a href=\"https://www.twitter.com/kudalimath\" target=\"_blank\" rel=\"noopener noreferrer\">Twitter</a></br>  "
          + "<a href=\"https://www.facebook.com/sriraghuvijayateertharu\" target=\"_blank\" rel=\"noopener noreferrer\">Facebook</a></br>  "
          + "<a href=\"https://www.instagram.com/sri_aryaakshobhyateerthamath\" target=\"_blank\" rel=\"noopener noreferrer\">Instagram</a></br>  "
          + "<a href=\"https://www.youtube.com/@srikudliaryaakshobhyateertmath\" target=\"_blank\" rel=\"noopener noreferrer\">Youtube</a>\r\n  "
          + "<p> &copy; Copyright <strong><span>Shri Kudali Aarya Akshobhya Teerth Matha</span></strong>. All Rights Reserved </p>\r\n"
          + "";
      
      message.setContent(htmlContent, "text/html; charset=utf-8");

      mailSender.send(message);
  }

    /**
     * @param contact
     * @throws MessagingException 
     * @throws AddressException 
     */
    public void sendContactHtmlEmail(Contact contact) throws AddressException, MessagingException {
      MimeMessage message = mailSender.createMimeMessage();

      message.setFrom(new InternetAddress("incarnationpath@gmail.com"));
      message.setRecipients(MimeMessage.RecipientType.TO, contact.getEmail());
      message.setSubject("Thank you for contacting Shri Kudali Aarya Akshobhya Teerth Matha");

      String htmlContent = "<h2>Shri Kudali Aarya Akshobhya Teerth Matha<h2>"
          + "<p>Dear Haribhakta "+contact.getName()+",</p>"
          
          + "Thank you for contacing Shri Kudali Aarya Akshobhya Teerth Matha. "
          + "We have received your message about "+contact.getSubject() +" and will get back to you soon."
                    
          + "<p>We hope you will continue to support us in our mission of spreading Madhva Siddhanta. "
          + "You can also follow us on our social media channels to stay updated on our latest news, "
          + "events, and activities. And don’t forget to check out our website for more math resources and opportunities.</p>"
          
          + "<p>Thank you again for contacting us!</p>"
          
          + "<p>Sincerely,</p>"
          
          + "<p>Shri Kudli Aarya Akshobhya Teerth Math</p>"
          + "<a href=\"https://www.srikudliaryaakshobhyateerthmath.org\" target=\"_blank\" rel=\"noopener noreferrer\">www.srikudliaryaakshobhyateerthmath.org</a> "
          + "<p>Get in touch with us via below social links</p>\r\n"
          + "<a href=\"https://www.twitter.com/kudalimath\" target=\"_blank\" rel=\"noopener noreferrer\">Twitter</a></br>  "
          + "<a href=\"https://www.facebook.com/sriraghuvijayateertharu\" target=\"_blank\" rel=\"noopener noreferrer\">Facebook</a></br>  "
          + "<a href=\"https://www.instagram.com/sri_aryaakshobhyateerthamath\" target=\"_blank\" rel=\"noopener noreferrer\">Instagram</a></br>  "
          + "<a href=\"https://www.youtube.com/@srikudliaryaakshobhyateertmath\" target=\"_blank\" rel=\"noopener noreferrer\">Youtube</a>\r\n  "
          + "<p> &copy; Copyright <strong><span>Shri Kudali Aarya Akshobhya Teerth Matha</span></strong>. All Rights Reserved </p>\r\n"
          + "";
      
      message.setContent(htmlContent, "text/html; charset=utf-8");

      mailSender.send(message);
      
      LOG.info("Sent contact from {} successfully!", contact.getEmail());
    }
}
