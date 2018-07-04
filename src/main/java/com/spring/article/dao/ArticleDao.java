package com.spring.article.dao;

import com.spring.article.dto.Article;
import com.spring.commons.paging.Criteria;

import java.util.List;

public interface ArticleDao {

  void createArticle(Article article);

  Article findByArticleNo(Integer articleNo);

  void updateArticle(Article article);

  void deleteArticle(Integer articleNo);

  List<Article> findAllArticle();

  List<Article> findAllArticlePaging(Criteria criteria);

  int countArticles(Criteria criteria);

}
