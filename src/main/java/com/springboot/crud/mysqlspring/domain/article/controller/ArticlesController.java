package com.springboot.crud.mysqlspring.domain.article.controller;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticlesController {

    @Autowired
    private final ArticleService articleService;

    @PostMapping("/post")
    public ArticleDto.SingleArticle<ArticleDto> createArticle(@RequestBody ArticleDto.SingleArticle<ArticleDto> article) {
        return new ArticleDto.SingleArticle<>(articleService.createArticle(article.getArticle()));
    }


}
