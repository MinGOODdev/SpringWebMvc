package com.spring.article.dao;

import com.spring.article.dto.Reply;

import java.util.List;

public interface ReplyDao {

  List<Reply> findAllReply(Integer articleNo);

  void createReply(Reply reply);

  void updateReply(Reply reply);

  void deleteReply(Integer replyNo);

}
