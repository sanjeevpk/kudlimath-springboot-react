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
 * @CreatedOn - 09-Aug-2023 8:27:39 pm
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
  
  private Long id;
  
  @NotBlank
  private String name;
  
  @Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
  private String mobile;
  
  @Email
  private String email;
  
  @NotBlank
  @Size(min=2, max=500)
  private String subject;
  
  @NotBlank
  @Size(min=4, max=2000)
  private String message;
  
}
