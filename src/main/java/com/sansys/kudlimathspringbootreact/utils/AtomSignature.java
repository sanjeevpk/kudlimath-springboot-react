/**
 * © Copyright SanSys Pvt. Ltd. All rights reserved. SanSys is a registered trademark and the SanSys
 * graphic logo is a trademark of SanSys Pvt. Ltd. SanSys reserves all the right for this source
 * code. You should not modify or reuse without the noticing it to SanSys. And need to provide
 * credits where applicable. Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific governing permissions and limitations under the License.
 *
 * @author - Sanjeev
 * @version - 1.0
 * @CreatedOn - 05-Oct-2023 2:10:16 pm
 * @Usage -
 *
 */

package com.sansys.kudlimathspringbootreact.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class AtomSignature {
  
  public static String generateSignature(String hashKey, String param) {
    String resp = null;
//    StringBuilder sb = new StringBuilder();
//    for (String s : param) {
//      sb.append(s);
//    }
    
    try {
      System.out.println("String =" + param);
      resp = byteToHexString(encodeWithHMACSHA2(param, hashKey));
      System.out.println("Final signature = "+resp);
      
    } catch (Exception e) {
      System.out.println("Unable to encocd value with key :" + hashKey + " and input :" + param);
      e.printStackTrace();
    }
    return resp;
  }

  private static byte[] encodeWithHMACSHA2(String text, String keyString)
      throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
    Key sk = new SecretKeySpec(keyString.getBytes("UTF-8"), "HMACSHA512");
    Mac mac = Mac.getInstance(sk.getAlgorithm());
    mac.init(sk);
    byte[] hmac = mac.doFinal(text.getBytes("UTF-8"));
    return hmac;
  }

  public static String byteToHexString(byte byData[]) {

    StringBuilder sb = new StringBuilder(byData.length * 2);
    for (int i = 0; i < byData.length; i++) {
      int v = byData[i] & 0xff;
      if (v < 16)
        sb.append('0');
      sb.append(Integer.toHexString(v));
    }
    return sb.toString();
  }
  
  public static void main(String args[]) {
    generateSignature("ea59e6ee036c81d8b5", "1191testQRUPI21.06INR");
  }
}
