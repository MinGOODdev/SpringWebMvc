package com.spring.mybatis.example.dao;

import com.spring.mybatis.example.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDaoImpl implements MemberDao {

  private static final String NAMESPACE = "com.spring.mybatis.example.dao.MemberDao";

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
