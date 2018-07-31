package com.spring.article.controller;

import com.spring.article.dto.Article;
import com.spring.article.service.ArticleService;
import com.spring.commons.paging.PageMaker;
import com.spring.commons.paging.SearchCriteria;
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
@RequestMapping("/article/paging/search")
public class PagingSearchArticleController {

  private static final Logger logger = LoggerFactory.getLogger(PagingSearchArticleController.class);

  private final ArticleService articleService;

  @Inject
  public PagingSearchArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  /**
   * 페이징 + 검색 처리 게시글 목록 조회
   *
   * @param searchCriteria
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                     Model model) throws Exception {

    logger.info("search list() called ...");

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCriteria(searchCriteria);
    pageMaker.setTotalCount(articleService.countSearchedArticles(searchCriteria));

    model.addAttribute("articles", articleService.findAllArticleSearch(searchCriteria));
    model.addAttribute("pageMaker", pageMaker);

    return "article/search/list_search";
  }

  /**
   * 페이징 + 검색 처리 해당 게시글 조회
   *
   * @param articleNo
   * @param searchCriteria
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/read", method = RequestMethod.GET)
  public String read(@RequestParam("articleNo") int articleNo,
                     @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                     Model model) throws Exception {

    logger.info("search read() called ...");

    model.addAttribute("article", articleService.findByArticleNo(articleNo));
    return "article/search/read_search";
  }

  /**
   * 페이징 + 검색 처리 게시글 수정 (GET)
   *
   * @param articleNo
   * @param searchCriteria
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/modify", method = RequestMethod.GET)
  public String modifyGet(@RequestParam("articleNo") int articleNo,
                          @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                          Model model) throws Exception {

    logger.info("search modifyGet() called ...");
    logger.info(searchCriteria.toString());

    model.addAttribute("article", articleService.findByArticleNo(articleNo));
    return "article/search/modify_search";
  }

  /**
   * 페이징 + 검색 처리 게시글 수정 (POST)
   *
   * @param article
   * @param searchCriteria
   * @param redirectAttributes
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/modify", method = RequestMethod.POST)
  public String modifyPost(Article article,
                           SearchCriteria searchCriteria,
                           RedirectAttributes redirectAttributes) throws Exception {

    logger.info("search modifyPost() called ...");

    articleService.updateArticle(article);
    redirectAttributes.addAttribute("page", searchCriteria.getPage());
    redirectAttributes.addAttribute("perPageNum", searchCriteria.getPerPageNum());
    redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
    redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());
    redirectAttributes.addFlashAttribute("msg", "modifySuccess");

    return "redirect:/article/paging/search/list_search";
  }

  /**
   * 페이징 + 검색 처리 삭제
   *
   * @param articleNo
   * @param searchCriteria
   * @param redirectAttributes
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public String remove(@RequestParam("articleNo") int articleNo,
                       SearchCriteria searchCriteria,
                       RedirectAttributes redirectAttributes) throws Exception {

    logger.info("search remove() called ...");

    articleService.deleteArticle(articleNo);
    redirectAttributes.addAttribute("page", searchCriteria.getPage());
    redirectAttributes.addAttribute("perPageNum", searchCriteria.getPerPageNum());
    redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
    redirectAttributes.addAttribute("keyword", searchCriteria.getSearchType());
    redirectAttributes.addFlashAttribute("msg", "removeSuccess");

    return "redirect:list";
  }

}
