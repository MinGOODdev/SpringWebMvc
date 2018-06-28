package com.spring.member.dao;

import com.spring.member.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDaoImpl implements MemberDao {

  private static final Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);

  private static final String NAMESPACE = "com.spring.member.dao.MemberDao";

  @Inject
  private SqlSession sqlSession;

  @Override
  public String getTime() {
    return sqlSession.selectOne(NAMESPACE + ".getTime");
  }

  @Override
  public void insertMember(Member member) {
    sqlSession.insert(NAMESPACE + ".insertMember", member);
  }

  @Override
  public Member findByUserId(String userId) {
    return sqlSession.selectOne(NAMESPACE + ".findByUserId", userId);
  }

  @Override
  public Member findByUserIdAndUserPw(String userId, String userPw) {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("userId", userId);
    paramMap.put("userPw", userPw);
    return sqlSession.selectOne(NAMESPACE + ".findByUserIdAndUserPw", paramMap);
  }
}
