package com.springboot.crud.mysqlspring.domain.article.service;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public ArticleDto createArticle(ArticleDto article) {

        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(article.getTitle())
                .description(article.getDescription())
                .author(article.getAuthor())
                .build();

        articleEntity = articleRepository.save(articleEntity);
        return convertEntityToDto(articleEntity, false, 0L);

    }

    private ArticleDto convertEntityToDto(ArticleEntity entity, Boolean favorited, Long favoritesCount) {
        return ArticleDto.builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .author(entity.getAuthor())
                .build();
    }



}
