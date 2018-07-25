package com.spring.article.controller;

import com.spring.article.dto.Reply;
import com.spring.article.service.ReplyService;
import com.spring.commons.paging.Criteria;
import com.spring.commons.paging.PageMaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies")
public class ReplyController {

  private final ReplyService replyService;

  @Inject
  public ReplyController(ReplyService replyService) {
    this.replyService = replyService;
  }

  /**
   * 댓글 목록 조회
   *
   * @param articleNo
   * @return
   */
  @RequestMapping(value = "/all/{articleNo}", method = RequestMethod.GET)
  public ResponseEntity<List<Reply>> list(@PathVariable("articleNo") Integer articleNo) {
    ResponseEntity<List<Reply>> entity = null;
    try {
      entity = new ResponseEntity<>(replyService.findAllReply(articleNo), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  /**
   * 댓글 등록 처리
   *
   * @param reply
   * @return
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> register(@RequestBody Reply reply) {
    ResponseEntity<String> entity = null;
    try {
      replyService.createReply(reply);
      entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  /**
   * 댓글 수정 처리
   *
   * @param replyNo
   * @param reply
   * @return
   */
  @RequestMapping(value = "/{replyNo}", method = {RequestMethod.PUT, RequestMethod.PATCH})
  public ResponseEntity<String> update(@PathVariable("replyNo") Integer replyNo,
                                       @RequestBody Reply reply) {
    ResponseEntity<String> entity = null;
    try {
      reply.setReplyNo(replyNo);
      replyService.updateReply(reply);
      entity = new ResponseEntity<>("modifySuccess", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  /**
   * 댓글 삭제 처리
   *
   * @param replyNo
   * @return
   */
  @RequestMapping(value = "/{replyNo}", method = RequestMethod.DELETE)
  public ResponseEntity<String> delete(@PathVariable("replyNo") Integer replyNo) {
    ResponseEntity<String> entity = null;
    try {
      replyService.deleteReply(replyNo);
      entity = new ResponseEntity<>("deleteSuccess", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  /**
   * 페이징 처리 댓글 목록 조회
   *
   * @param articleNo
   * @param page
   * @return
   */
  @RequestMapping(value = "/{articleNo}/{page}", method = RequestMethod.GET)
  public ResponseEntity<Map<String, Object>> listPaging(@PathVariable("articleNo") Integer articleNo,
                                                        @PathVariable("page") Integer page) {
    ResponseEntity<Map<String, Object>> entity = null;

    try {
      Criteria criteria = new Criteria();
      criteria.setPage(page);

      List<Reply> replies = replyService.findAllReplyPaging(articleNo, criteria);
      int repliesCount = replyService.countReplies(articleNo);

      PageMaker pageMaker = new PageMaker();
      pageMaker.setCriteria(criteria);
      pageMaker.setTotalCount(repliesCount);

      Map<String, Object> map = new HashMap<>();
      map.put("replies", replies);
      map.put("pageMaker", pageMaker);

      entity = new ResponseEntity<>(map, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

}
