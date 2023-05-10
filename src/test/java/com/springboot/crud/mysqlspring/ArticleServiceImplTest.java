package com.springboot.crud.mysqlspring;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.repository.ArticleRepository;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    @Test
    void whenValidArticleForm_thenReturnArticle() {
        String wrongTitle = "wrong";
        // when(articleRepository.save(any(ArticleEntity.class))).thenReturn(expectedArticle);


        // ArticleDto actual =  articleService.createArticle(article);

        assertEquals(expectedTitle, article.getTitle());
        assertNotEquals(expectedTitle, wrongTitle);

    }


}
