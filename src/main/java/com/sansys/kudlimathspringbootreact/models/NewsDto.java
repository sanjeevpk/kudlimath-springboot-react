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
 * @CreatedOn - 28-Sept-2023 12:07:57 am
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.models;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
  
  private Long id;
  private LocalDateTime creationDateTime;
  private String createdBy;
  
  @Size(min = 10, max = 2500)
  private String title;
  
  @Size(min = 10, max = 25000)
  private String description;
  
  private String labels;
  
  
}
