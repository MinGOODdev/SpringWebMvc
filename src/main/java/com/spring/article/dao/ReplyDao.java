package com.spring.article.dao;

import com.spring.article.dto.Reply;
import com.spring.commons.paging.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyDao {

  List<Reply> findAllReply(Integer articleNo);

  void createReply(Reply reply);

  void updateReply(Reply reply);

  void deleteReply(Integer replyNo);

  List<Reply> findAllReplyPaging(@Param("articleNo") Integer articleNo,
                                 @Param("criteria") Criteria criteria);

  int countReplies(Integer articleNo);

}
