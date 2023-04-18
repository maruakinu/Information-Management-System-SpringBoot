package com.springboot.crud.mysqlspring.domain.article.service;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;

public interface ArticleService {

    ArticleDto createArticle(final ArticleDto article);

    ArticleDto getArticle(final String title);

    void deleteArticle(final String title);

    ArticleDto updateArticle(final String title, final ArticleDto.Update article);


}
