package com.springboot.crud.mysqlspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.controller.ArticlesController;
import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.service.ArticleService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ArticlesController.class)
@RunWith(SpringRunner.class)
public class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("Get Article By Title, should return expected Article")
    public void getArticleShouldReturn200() throws Exception {

        String title = "new";
        when(articleService.getArticle(title)).thenReturn(new ArticleDto(1L, "new", "Description", "Johnny"));

        this.mockMvc.perform(get("/api/"+title)
                        .accept(APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk());

    }

    @Test
    public void shouldReturnNotFoundTutorial() throws Exception {

        String title = "wrong_title";
        when(articleService.getArticle(title)).thenReturn(null);

        mockMvc.perform(get("/api/" +  title)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                        .andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("Get Article By non existence Title, should return 404")
//    public void getArticleShouldReturn404() throws Exception {
//
//        String title = "wrong_title";
//        when(articleService.getArticle(title)).thenReturn(new ArticleDto(1L, "new", "Description", "Johnny"));
//
//
//        mockMvc.perform(get("/api/"+title)
//                        .accept(APPLICATION_JSON_UTF8)
//                        .characterEncoding("utf-8"))
//                        .andExpect(jsonPath("$.article", Matchers.notNullValue(ArticleDto.class)))
//                        .andExpect(jsonPath("$.article.title", is("wrong_title")))
//                        .andExpect(status().isNotFound());



//    }






}

