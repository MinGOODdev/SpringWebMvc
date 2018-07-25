package com.spring.article.controller;

import com.spring.article.dto.Article;
import com.spring.article.service.ArticleService;
import com.spring.commons.paging.Criteria;
import com.spring.commons.paging.PageMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("/article")
public class PagingArticleController {

  private static final Logger logger = LoggerFactory.getLogger(PagingArticleController.class);

  private final ArticleService articleService;

  @Inject
  public PagingArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  /**
   * 페이징 처리: 전체 목록
   *
   * @param model
   * @param criteria
   * @return
   */
  @RequestMapping(value = "/listPaging", method = RequestMethod.GET)
  public String listPaging(Model model, Criteria criteria) {
    logger.info("listPaging ...");

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCriteria(criteria);
    pageMaker.setTotalCount(articleService.countArticles(criteria));

    model.addAttribute("articles", articleService.findAllArticlePaging(criteria));
    model.addAttribute("pageMaker", pageMaker);

    return "article/paging/list_paging";
  }

  /**
   * 페이징 처리: 게시글 조회
   *
   * @param articleNo
   * @param criteria
   * @param model
   * @return
   */
  @RequestMapping(value = "/readPaging", method = RequestMethod.GET)
  public String readPaging(@RequestParam("articleNo") int articleNo,
                           @ModelAttribute("criteria") Criteria criteria,
                           Model model) {
    model.addAttribute("article", articleService.findByArticleNo(articleNo));
    return "article/paging/read_paging";
  }

  /**
   * 페이징 처리: 게시글 수정 GET
   * @param articleNo
   * @param criteria
   * @param model
   * @return
   */
  @RequestMapping(value = "/modifyPaging", method = RequestMethod.GET)
  public String modifyGetPaging(@RequestParam("articleNo") int articleNo,
                                @ModelAttribute("criteria") Criteria criteria,
                                Model model) {
    logger.info("modifyGetPaging ...");
    model.addAttribute("article", articleService.findByArticleNo(articleNo));
    return "article/paging/modify_paging";
  }

  /**
   * 페이징 처리: 게시글 수정 POST
   * @param article
   * @param criteria
   * @param redirectAttributes
   * @return
   */
  @RequestMapping(value = "/modifyPaging", method = RequestMethod.POST)
  public String modifyPostPaging(Article article,
                                 Criteria criteria,
                                 RedirectAttributes redirectAttributes) {
    logger.info("modifyPostPaging ...");
    articleService.updateArticle(article);
    redirectAttributes.addAttribute("page", criteria.getPage());
    redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
    redirectAttributes.addFlashAttribute("msg", "modifyPagingSuccess");

    return "redirect:listPaging";
  }

  @RequestMapping(value = "/deletePaging", method = RequestMethod.POST)
  public String deletePaging(@RequestParam("articleNo") int articleNo,
                             Criteria criteria,
                             RedirectAttributes redirectAttributes) {
    logger.info("removePaging ...");
    articleService.deleteArticle(articleNo);
    redirectAttributes.addAttribute("page", criteria.getPage());
    redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
    redirectAttributes.addFlashAttribute("msg", "deletePagingSuccess");

    return "redirect:listPaging";
  }

}
