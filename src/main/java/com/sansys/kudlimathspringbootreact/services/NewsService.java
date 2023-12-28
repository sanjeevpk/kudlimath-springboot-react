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
 * @CreatedOn - 29-Sept-2023 1:01:40 am
 * @Usage - 
 *
 */

package com.sansys.kudlimathspringbootreact.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sansys.kudlimathspringbootreact.entities.News;
import com.sansys.kudlimathspringbootreact.models.NewsDto;
import com.sansys.kudlimathspringbootreact.repositories.NewsRepository;

/**
 * 
 */

@Service
public class NewsService {

  private static final Logger LOG = LoggerFactory.getLogger(NewsService.class);
  
  @Autowired
  private NewsRepository newsRepository;
  
  /**
   * @param newsDto
   * @return
   */
  public NewsDto createNews(NewsDto newsDto) {
    LOG.info("createNews() service method");
    List<String> tokenizedLabels = getTokensWithCollection(newsDto.getLabels());
    
    News news = new News();
    news.setTitle(newsDto.getTitle());
    news.setDescription(newsDto.getDescription());
    news.setCreatedBy("ADMIN");
    news.setCreationDateTime(LocalDateTime.now());
    news.setLabels(tokenizedLabels);
    
    news = newsRepository.save(news);
    
    newsDto.setId(news.getId());
    newsDto.setTitle(news.getTitle());
    newsDto.setDescription(news.getDescription());
    newsDto.setLabels(news.getLabels().toString());
    newsDto.setCreatedBy(news.getCreatedBy());
    newsDto.setCreationDateTime(news.getCreationDateTime());
    return newsDto;
  }
  
  public List<String> getTokensWithCollection(String str) {
    return Collections.list(new StringTokenizer(str, ",")).stream()
      .map(token -> (String) token)
      .collect(Collectors.toList());
  }

}
