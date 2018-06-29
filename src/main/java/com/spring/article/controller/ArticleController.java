package com.spring.article.controller;

import com.spring.article.dto.Article;
import com.spring.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("/article")
public class ArticleController {

  private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

  private final ArticleService articleService;

  @Inject
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  /**
   * 전체 목록
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Model model) {
    logger.info("list ...");
    model.addAttribute("articles", articleService.findAllArticle());

    return "/article/list";
  }

  /**
   * 해당 article 조회
   *
   * @param articleNo
   * @param model
   * @return
   */
  @RequestMapping(value = "/read", method = RequestMethod.GET)
  public String findOne(@RequestParam("articleNo") int articleNo, Model model) {
    logger.info("findOne ...");
    model.addAttribute("article", articleService.findByArticleNo(articleNo));

    return "/article/read";
  }

  /**
   * article 등록 화면으로 이동
   *
   * @return
   */
  @RequestMapping(value = "/write", method = RequestMethod.GET)
  public String writeGet() {
    logger.info("write GET ...");

    return "/article/write";
  }

  /**
   * article 등록
   *
   * @param article
   * @param redirectAttributes
   * @return
   */
  @RequestMapping(value = "/write", method = RequestMethod.POST)
  public String writePost(Article article, RedirectAttributes redirectAttributes) {
    logger.info("write POST ...");
    logger.info(article.toString());
    articleService.createArticle(article);
    redirectAttributes.addFlashAttribute("msg", "regSuccess");

    return "redirect:list";
  }

  /**
   * article 수정 화면으로 이동
   *
   * @param articleNo
   * @param model
   * @return
   */
  @RequestMapping(value = "/modify", method = RequestMethod.GET)
  public String modifyGet(@RequestParam("articleNo") int articleNo, Model model) {
    logger.info("modify Get ...");
    model.addAttribute("article", articleService.findByArticleNo(articleNo));

    return "/article/modify";
  }

  /**
   * article 수정
   *
   * @param article
   * @param redirectAttributes
   * @return
   */
  @RequestMapping(value = "/modify", method = RequestMethod.POST)
  public String modifyPost(Article article, RedirectAttributes redirectAttributes) {
    logger.info("modify POST ...");
    articleService.updateArticle(article);
    redirectAttributes.addFlashAttribute("msg", "modifySuccess");

    return "redirect:list";
  }

  /**
   * article 삭제
   *
   * @param articleNo
   * @param redirectAttributes
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String delete(@RequestParam("articleNo") int articleNo, RedirectAttributes redirectAttributes) {
    logger.info("delete ...");
    articleService.deleteArticle(articleNo);
    redirectAttributes.addFlashAttribute("msg", "deleteSuccess");

    return "redirect:list";
  }

}
