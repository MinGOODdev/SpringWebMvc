package com.spring.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExRedirectController {

  private static final Logger logger = LoggerFactory.getLogger(ExRedirectController.class);

  @RequestMapping("/doE")
  public String doE(RedirectAttributes redirectAttributes) {
    logger.info("/doE called and redirect do /doF");
    redirectAttributes.addAttribute("msg", "msg with redirect");
    return "redirect:/doF";
  }

  @RequestMapping("/doF")
  public void doF(@RequestParam String msg) {
    logger.info("/doF called ... " + msg);
  }

}
