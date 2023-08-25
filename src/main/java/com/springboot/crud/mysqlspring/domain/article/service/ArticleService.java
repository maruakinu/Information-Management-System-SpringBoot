package com.springboot.crud.mysqlspring.domain.article.service;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.security.AuthUserDetails;

import java.util.List;

public interface ArticleService {

    ArticleDto createArticle(final ArticleDto article, final AuthUserDetails authUserDetails);

    ArticleDto getArticle(final String title, final AuthUserDetails authUserDetails);

    void deleteArticle(final String title, final AuthUserDetails authUserDetails);

    ArticleDto updateArticle(final String title, final ArticleDto.Update article, final AuthUserDetails authUserDetails);

//    List<ArticleDto> getAllArticles();

    List<ArticleDto> getAllArticlesWithUserId(final AuthUserDetails authUserDetails);

}
