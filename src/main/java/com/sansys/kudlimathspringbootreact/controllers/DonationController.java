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
 * @CreatedOn - 05-Sept-2023 10:19:11 pm
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.controllers;

import javax.naming.directory.InvalidAttributesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sansys.kudlimathspringbootreact.models.DonateesModel;
import com.sansys.kudlimathspringbootreact.services.DonateesService;
import jakarta.validation.Valid;

/**
 * 
 */

@RestController
@RequestMapping("/api/v1")
public class DonationController {
  
  private static final Logger LOG = LoggerFactory.getLogger(DonationController.class);
  
  @Autowired
  private DonateesService donateesService;
  
  @PostMapping("/donation")
  public ResponseEntity<?> donate(@Valid @RequestBody DonateesModel donateesModel) throws InvalidAttributesException{
    LOG.info("/donation API is called");
    return new ResponseEntity<>(donateesService.donate(donateesModel), HttpStatus.CREATED);
  } 
  
}
