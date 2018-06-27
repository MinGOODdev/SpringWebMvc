package dao;

import com.spring.dao.MemberDaoImpl;
import com.spring.dto.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/**/*.xml"})
public class MemberDaoImplTest {

  @Inject
  private MemberDaoImpl memberDaoImpl;

  @Test
  public void testGetTime() throws Exception {
    System.out.println(memberDaoImpl.getTime());
  }

  @Test
  public void testInsertMember() throws Exception {
    Member member = new Member();
    member.setUserId("user01");
    member.setUserPw("user01");
    member.setUserName("user01");
    member.setEmail("user@mail.com");
    memberDaoImpl.insertMember(member);
  }

  @Test
  public void testFindByUserId() throws Exception {
    memberDaoImpl.findByUserId("user01");
  }

  @Test
  public void testFindByUserIdAndUserPw() throws Exception {
    memberDaoImpl.findByUserIdAndUserPw("user01", "user01");
  }

}
