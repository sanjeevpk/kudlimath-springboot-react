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
 * @CreatedOn - 29-Sept-2023 12:52:39 am
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.entities;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
    
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
    
  private String title;
  
  private String description;
  
  private List<String> labels;
  
  private String createdBy;
  
  @Column(name = "date_time", columnDefinition = "TIMESTAMP")
  private LocalDateTime creationDateTime;
  
}
