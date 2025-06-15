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
import com.sansys.kudlimathspringbootreact.entities.Donatees;
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

    public void sendHtmlEmail(Donatees donatees) throws MessagingException {
      LOG.info("Email initiating");
      MimeMessage message = mailSender.createMimeMessage();

      message.setFrom(new InternetAddress("kudlimath@gmail.com"));
      message.setRecipients(MimeMessage.RecipientType.TO, donatees.getEmail());
      message.setSubject("Thank you for your generous contributions!");

      String donorName = donatees.getName();
      String greetingName = (donorName != null && !donorName.trim().isEmpty()) ? " " + donorName : "";
      String htmlContent = "<span style=\"color:#4A2C2A;font-size:18px;font-weight:bold;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Dear Haribhakta "+ greetingName  +"</p></span>"
          + "\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Thank you for your generous contribution to Shree Kudli Aarya Akshobhya Teerth Math. Your support is invaluable and will significantly aid our efforts to continue and expand our sevas.</p></span>\r\n"
          + "\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Through your donation, you are making a tangible difference in the lives of many Madhva devotees and helping us advance our vision: a world where the Math's profound contributions to society are widely recognized and cherished.</p></span>\r\n"
          + "\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>We sincerely hope you will continue to support our mission of spreading Madhva Siddhanta. We also offer our prayers to Hari, Vayu, and Gurugalu for your continued good health, joy, abundance, and strength to perform further sevas. We invite you to stay updated on our latest news, events, and activities by following us on our social media channels. For more comprehensive Math information, resources, and opportunities for seva, please visit our website.</p></span>\r\n"
          + "\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Once again, thank you for your kindness and generosity. We look forward to your continued association.</p></span>\r\n"
          + "\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-weight:bold;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Sincerely,</p></span>\r\n"
          + "\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-weight:bold;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Shree Kudli Aarya Akshobhya Teerth Math</p></span>\r\n"
          + "<a href=\"https://www.kudlimath.in\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color:#8B4513;font-family:Calibri, Helvetica, Arial, Sans-Serif;font-size:16px;\">www.kudlimath.in</a>\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-weight:bold;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>Connect with us on social media:</p></span>\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<a href=\"https://www.twitter.com/kudlimath\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color:#8B4513;font-size:16px;\">Twitter</a>\r\n"
          + "</span><br>\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<a href=\"https://www.facebook.com/sriraghuvijayateertharu\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color:#8B4513;font-size:16px;\">Facebook</a>\r\n"
          + "</span><br>\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<a href=\"https://www.instagram.com/sri_aryaakshobhyateerthamath\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color:#8B4513;font-size:16px;\">Instagram</a>\r\n"
          + "</span><br>\r\n"
          + "<span style=\"color:#4A2C2A;font-size:16px;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<a href=\"https://www.youtube.com/@srikudliaryaakshobhyateertmath\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color:#8B4513;font-size:16px;\">Youtube</a>\r\n"
          + "</span>\r\n"
          + "<span style=\"color:#4A2C2A;font-size:14px;font-weight:bold;font-family:Calibri, Helvetica, Arial, Sans-Serif;\">\r\n"
          + "<p>&copy; Copyright <strong><span>Shree Kudali Aarya Akshobhya Teerth Matha</span></strong>. All Rights Reserved </p></span>\r\n"
          + "";
      
      message.setContent(htmlContent, "text/html; charset=utf-8");

      mailSender.send(message);
      LOG.info("Email sent");
  }

    /**
     * @param contact
     * @throws MessagingException 
     * @throws AddressException 
     */
    public void sendContactHtmlEmail(Contact contact) throws AddressException, MessagingException {
      LOG.info("Email initiating");
      MimeMessage message = mailSender.createMimeMessage();

      message.setFrom(new InternetAddress("kudlimath@gmail.com"));
      message.setRecipients(MimeMessage.RecipientType.TO, contact.getEmail());
      message.setSubject("Thank you for contacting Shree Kudali Aarya Akshobhya Teerth Matha");

      String htmlContent = ""
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p>Dear Haribhakta "+contact.getName()+",</p></span>"
          
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">\r\n"
          + "Thank you for contacting Shree Kudali Aarya Akshobhya Teerth Matha. "
          + "We have received your message about "+contact.getSubject() +" and will get back to you soon.</span>"
                    
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p>We hope you will continue to support us in our mission of spreading Madhva Siddhanta. "
          + "You can also follow us on our social media channels to stay updated on our latest news, "
          + "events, and activities. And don’t forget to check out our website for more math resources and opportunities.</p></span>"
          
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p>Thank you again for contacting us!</p></span>"
          
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p>Sincerely,</p></span>"
          
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p>Shree Kudli Aarya Akshobhya Teerth Math</p></span>"
          + "<a href=\"https://www.kudlimath.in\" target=\"_blank\" rel=\"noopener noreferrer\">www.kudlimath.in</a> "
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p>Get in touch with us via below social links</p></span>\n"
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<a href=\"https://www.twitter.com/kudlimath\" target=\"_blank\" rel=\"noopener noreferrer\">Twitter | </a></span></br>  "
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<a href=\"https://www.facebook.com/sriraghuvijayateertharu\" target=\"_blank\" rel=\"noopener noreferrer\">Facebook | </a></span></br>  "
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<a href=\"https://www.instagram.com/sri_aryaakshobhyateerthamath\" target=\"_blank\" rel=\"noopener noreferrer\">Instagram | </a></span></br>  "
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<a href=\"https://www.youtube.com/@srikudliaryaakshobhyateertmath\" target=\"_blank\" rel=\"noopener noreferrer\">Youtube</a></span>\r\n  "
          + "<span style=\"color:#613400;font-size:14px;font-weight:bold;font-family:Calibri,Helvetica, Arial, Sans-Serif;\">"
          + "<p> &copy; Copyright <strong><span>Shree Kudali Aarya Akshobhya Teerth Matha</span></strong>. All Rights Reserved </p></span>\r\n"
          + "";
      
      message.setContent(htmlContent, "text/html; charset=utf-8");

      mailSender.send(message);
      
      LOG.info("Sent contact from {} successfully!", contact.getEmail());
    }
}
