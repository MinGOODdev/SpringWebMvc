package com.spring.mybatis.example.dao;

import com.spring.mybatis.example.dto.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {

  String getTime();

  void insertMember(Member member);

  Member findByUserId(String userId);

  Member findByUserIdAndUserPw(@Param("userId") String userId, @Param("userPw") String userPw);

}
