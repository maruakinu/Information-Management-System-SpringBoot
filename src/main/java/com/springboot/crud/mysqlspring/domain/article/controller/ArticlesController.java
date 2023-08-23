package com.springboot.crud.mysqlspring.domain.article.controller;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleService;
import com.springboot.crud.mysqlspring.security.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ArticleDto.SingleArticle<ArticleDto> createArticle(@RequestBody @Valid ArticleDto.SingleArticle<ArticleDto> article, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return new ArticleDto.SingleArticle<>(articleService.createArticle(article.getArticle(), authUserDetails));
    }

    @GetMapping("/{title}")
    public ArticleDto.SingleArticle<ArticleDto> getArticle(@PathVariable String title, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return new ArticleDto.SingleArticle<>(articleService.getArticle(title, authUserDetails));
    }

    @DeleteMapping("/{title}")
    public void deleteArticle(@PathVariable String title, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        articleService.deleteArticle(title, authUserDetails);
    }

    @PutMapping("/{title}")
    public ArticleDto.SingleArticle<ArticleDto> createArticle(@PathVariable String title, @RequestBody ArticleDto.SingleArticle<ArticleDto.Update> article, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return new ArticleDto.SingleArticle<>(articleService.updateArticle(title, article.getArticle(), authUserDetails));
    }

    @GetMapping("/all")
    public ArticleDto.MultipleArticle getArticle() {
        return ArticleDto.MultipleArticle.builder()
                .articles(articleService.getAllArticles())
                .build();
    }

    @GetMapping("/articles")
    public ArticleDto.MultipleArticle get_All_Articles_Associated_By_UserId(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return ArticleDto.MultipleArticle.builder()
                .articles(articleService.getAllArticlesWithUserId(authUserDetails))
                .build();
    }


}
