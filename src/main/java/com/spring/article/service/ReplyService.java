package com.spring.article.service;

import com.spring.article.dto.Reply;

import java.util.List;

public interface ReplyService {

  List<Reply> findAllReply(Integer articleNo);

  void createReply(Reply reply);

  void updateReply(Reply reply);

  void deleteReply(Integer replyNo);

}
