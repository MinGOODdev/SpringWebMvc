package dao;

import com.spring.dao.MemberDao;
import com.spring.dto.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class MemberDaoTest {

  @Inject
  private MemberDao memberDao;

  @Test
  public void testGetTime() throws Exception {
    System.out.println(memberDao.getTime());
  }

  @Test
  public void testInsertMember() throws Exception {
    Member member = new Member();
    member.setUserId("user00");
    member.setUserPw("user00");
    member.setUserName("MinGOOD");
    member.setEmail("Min@mail.com");
    memberDao.insertMember(member);
  }

  @Test
  public void testFindByUserId() throws Exception {
    memberDao.findByUserId("user00");
  }

  @Test
  public void testFindByUserIdAndUserPw() throws Exception {
    memberDao.findByUserIdAndUserPw("user00", "user00");
  }

}
