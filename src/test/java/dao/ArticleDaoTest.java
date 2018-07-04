package dao;

import com.spring.article.dao.ArticleDao;
import com.spring.article.dto.Article;
import com.spring.commons.paging.Criteria;
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
public class ArticleDaoTest {

  private static final Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);

  @Inject
  private ArticleDao articleDao;

  @Test
  public void testCreateArticle() throws Exception {
    Article article = new Article();
    article.setTitle("new title");
    article.setContent("new content");
    article.setWriter("MinGOOD");
    articleDao.createArticle(article);
  }

  @Test
  public void testFindByArticleNo() throws Exception {
    logger.info(articleDao.findByArticleNo(1).toString());
  }

  @Test
  public void testUpdateArticle() throws Exception {
    Article article = new Article();
    article.setArticleNo(1);
    article.setTitle("update title");
    article.setContent("update content");
    articleDao.updateArticle(article);
  }

  @Test
  public void testDeleteArticle() throws Exception {
    articleDao.deleteArticle(1);
  }

  @Test
  public void testFindAllArticle() throws Exception {
    List<Article> articles = articleDao.findAllArticle();
    for (Article a : articles) {
      logger.info("title: {} content: {}", a.getTitle(), a.getContent());
    }
  }

  @Test
  public void testFindAllArticlePaging() throws Exception {
    Criteria criteria = new Criteria();
    criteria.setPage(3);
    criteria.setPerPageNum(20);

    List<Article> articles = articleDao.findAllArticlePaging(criteria);

    for (Article article : articles) {
      logger.info(article.getArticleNo() + ": " + article.getTitle());
    }
  }

  /**
   * 더미 데이터를 삽입하는 코드
   *
   * @throws Exception
   */
  @Test
  public void createDummyData() throws Exception {
    for (int i = 0; i < 1000; ++i) {
      Article article = new Article();
      article.setTitle(i + "번째 title");
      article.setContent(i + "번째 content");
      article.setWriter("user" + i);
      articleDao.createArticle(article);
    }
  }

}
