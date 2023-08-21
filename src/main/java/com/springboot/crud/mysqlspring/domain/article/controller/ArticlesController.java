package com.springboot.crud.mysqlspring.domain.article.controller;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticlesController {

    @Autowired
    private final ArticleService articleService;

    // Method
    @PostMapping("/post")
    public ArticleDto.SingleArticle<ArticleDto> createArticle(@RequestBody @Valid ArticleDto.SingleArticle<ArticleDto> article) {
        return new ArticleDto.SingleArticle<>(articleService.createArticle(article.getArticle()));
    }

    @GetMapping("/{title}")
    public ArticleDto.SingleArticle<ArticleDto> getArticle(@PathVariable String title) {
        return new ArticleDto.SingleArticle<>(articleService.getArticle(title));
    }

    @DeleteMapping("/{title}")
    public void deleteArticle(@PathVariable String title) {
        articleService.deleteArticle(title);
    }

    @PutMapping("/{title}")
    public ArticleDto.SingleArticle<ArticleDto> createArticle(@PathVariable String title, @RequestBody ArticleDto.SingleArticle<ArticleDto.Update> article) {
        return new ArticleDto.SingleArticle<>(articleService.updateArticle(title, article.getArticle()));
    }

    @GetMapping("/all")
    public ArticleDto.MultipleArticle getArticle() {
        return ArticleDto.MultipleArticle.builder()
                .articles(articleService.getAllArticles())
                .build();
    }


}
