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
 * @CreatedOn - 17-Aug-2023 11:21:59 pm
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sansys.kudlimathspringbootreact.entities.Donatees;



/**
 * 
 */

@Repository
public interface DonateesRepository extends JpaRepository<Donatees, Long>{
  public Donatees findByNameAndMobileAndEmailAndAmountAndDateTime(
      String name, String mobile, String email, BigDecimal amount, LocalDateTime dateTime);
  
  public Donatees findByNameAndMobileAndEmailAndAmount(
      String name, String mobile, String email, BigDecimal amount);
  
  public Donatees findByMerchantIdAndAtomTokenIdAndMerchantTnxId(String merchantId, String atomTokenId, String merchantTnxId);

  /**
   * @param string
   * @param string2
   * @return
   */
  public Donatees findByMerchantIdAndMerchantTnxId(String merchantId, String merchantTnxId);
}
