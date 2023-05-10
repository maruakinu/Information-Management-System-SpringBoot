package com.springboot.crud.mysqlspring;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.repository.ArticleRepository;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    ArticleServiceImpl articleService;

    @Mock
    ArticleRepository articleRepository;


    private ArticleDto article;

    private ArticleEntity expectedArticle;

    private String expectedTitle;

    @BeforeEach
    void setUp() {

        article = ArticleDto.builder()
                .title("title")
                .description("description")
                .author("Marlo")
                .build();

        expectedTitle = article.getTitle();

        expectedArticle = ArticleEntity.builder()
                .title(expectedTitle)
                .description(article.getDescription())
                .author(article.getAuthor())
                .build();

    }




}
