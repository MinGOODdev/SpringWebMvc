package com.spring.article.dao;

import com.spring.article.dto.Article;

import java.util.List;

public interface ArticleDao {

  void createArticle(Article article);

  Article findByArticleNo(Integer articleNo);

  void updateArticle(Article article);

  void deleteArticle(Integer no);

  List<Article> findAllArticle();

}
