package com.spring.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 화면에서 AJAX 호출 테스트
 */
@Controller
@RequestMapping("/reply")
public class ReplyTestController {

  @RequestMapping("/test")
  public String replyAjaxTest() {
    return "/tutorial/reply_test";
  }

}
