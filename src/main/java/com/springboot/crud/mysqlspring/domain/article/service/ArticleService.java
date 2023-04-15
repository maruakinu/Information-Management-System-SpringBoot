package com.springboot.crud.mysqlspring.domain.article.service;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;

public interface ArticleService {

    ArticleDto createArticle(final ArticleDto article);

    ArticleDto getArticle(final String title);

}