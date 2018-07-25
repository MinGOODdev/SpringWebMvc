package dao;

import com.spring.article.dao.ReplyDao;
import com.spring.article.dto.Reply;
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
public class ReplyDaoTest {

  private static final Logger logger = LoggerFactory.getLogger(ReplyDaoTest.class);

  @Inject
  private ReplyDao replyDao;

  @Test
  public void 댓글_생성() throws Exception {
    for (int i = 1; i <= 100; ++i) {
      Reply reply = new Reply();
      reply.setArticleNo(1);
      reply.setReplyText(i + "번째 댓글입니다.");
      reply.setReplyWriter("user" + i);
      replyDao.createReply(reply);
    }
  }

  @Test
  public void 댓글_목록_조회() throws Exception {
    logger.info(replyDao.findAllReply(1).toString());
  }

  @Test
  public void 댓글_수정() throws Exception {
    Reply reply = new Reply();
    reply.setArticleNo(1);
    reply.setReplyNo(2);
    reply.setReplyText(2 + "번째 댓글 수정");
    replyDao.updateReply(reply);
  }

  @Test
  public void 댓글_삭제() throws Exception {
    replyDao.deleteReply(3);
  }

  @Test
  public void 댓글_페이징() throws Exception {
    Criteria criteria = new Criteria();
    criteria.setPerPageNum(20);
    criteria.setPage(1);

    List<Reply> replies = replyDao.findAllReplyPaging(1, criteria);

    for (Reply reply : replies) {
      logger.info(reply.getReplyNo() + " : " + reply.getReplyText());
    }
  }

}
