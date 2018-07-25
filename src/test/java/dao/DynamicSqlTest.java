package dao;

import com.spring.article.dao.ArticleDao;
import com.spring.article.dto.Article;
import com.spring.commons.paging.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class DynamicSqlTest {

  private static final Logger logger = LoggerFactory.getLogger(DynamicSqlTest.class);

  @Inject
  private ArticleDao articleDao;

  @Test
  public void testDynamic1() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setPage(1);
    searchCriteria.setKeyword("998");
    searchCriteria.setSearchType("title");

    logger.info("=================");

    List<Article> articles = articleDao.findAllArticleSearch(searchCriteria);

    for (Article article : articles) {
      logger.info(article.getArticleNo() + " : " + article.getTitle());
    }

    logger.info("=================");
    logger.info("searched articles count : " + articleDao.countSearchedArticles(searchCriteria));
  }

}
