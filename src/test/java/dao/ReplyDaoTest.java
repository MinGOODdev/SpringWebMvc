package dao;

import com.spring.article.dao.ReplyDao;
import com.spring.article.dto.Reply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ReplyDaoTest {

  private static final Logger logger = LoggerFactory.getLogger(ReplyDaoTest.class);

  @Inject
  private ReplyDao replyDao;

  @Test
  public void testReplyCreate() throws Exception {
    for (int i = 1; i <= 100; ++i) {
      Reply reply = new Reply();
      reply.setArticleNo(1);
      reply.setReplyText(i + "번째 댓글입니다.");
      reply.setReplyWriter("user" + i);
      replyDao.createReply(reply);
    }
  }

  @Test
  public void testReplyList() throws Exception {
    logger.info(replyDao.findAllReply(1).toString());
  }

  @Test
  public void testReplyUpdate() throws Exception {
    Reply reply = new Reply();
    reply.setArticleNo(1);
    reply.setReplyNo(2);
    reply.setReplyText(2 + "번째 댓글 수정");
    replyDao.updateReply(reply);
  }

  @Test
  public void testReplyDelete() throws Exception {
    replyDao.deleteReply(3);
  }

}
