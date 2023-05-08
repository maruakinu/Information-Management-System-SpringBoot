package com.springboot.crud.mysqlspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.controller.ArticlesController;
import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ArticlesController.class)
@DisplayName("Author Resource Integration Tests")
public class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    private ArticleDto article;

    private ArticleDto.SingleArticle singleArticle;


    @Test
    @DisplayName("create Article, should return expected 200")
    public void createAuthorShouldReturn201() throws Exception {
        //given
        article = ArticleDto.builder().title("title").description("description").author("Marlo").build();
        singleArticle = ArticleDto.SingleArticle.builder().article(article).build();
//        String json = objectMapper.writeValueAsString(article);
        when(articleService.createArticle(any(ArticleDto.class))).thenReturn(article);

        //when-then
        mockMvc.perform(post("/api/post")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(singleArticle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.article", Matchers.notNullValue(ArticleDto.class)));
    }








}

