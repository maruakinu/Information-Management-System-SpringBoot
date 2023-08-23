package com.springboot.crud.mysqlspring.domain.article.service;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.repository.ArticleRepository;
import com.springboot.crud.mysqlspring.domain.exception.AppException;
import com.springboot.crud.mysqlspring.domain.exception.Error;
import com.springboot.crud.mysqlspring.domain.user.entity.UserEntity;
import com.springboot.crud.mysqlspring.domain.user.repository.UserRepository;
import com.springboot.crud.mysqlspring.security.AuthUserDetails;
import com.springboot.crud.mysqlspring.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public ArticleDto createArticle(ArticleDto article, AuthUserDetails authUserDetails) {
        UserEntity author = UserEntity.builder()
                .id(String.valueOf(authUserDetails.getId()))
                .build();

        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(article.getTitle())
                .description(article.getDescription())
                .author(author)
                .build();

        articleEntity = articleRepository.save(articleEntity);
        return convertEntityToDtos(articleEntity, authUserDetails);

    }

    @Override
    public ArticleDto getArticle(String title, AuthUserDetails authUserDetails) {
        ArticleEntity found = articleRepository.findByTitle(title);
        return convertEntityToDtos(found, authUserDetails);
    }

    @Override
    public List<ArticleDto> getAllArticlesWithUserId(AuthUserDetails authUserDetails) {
        List<ArticleEntity> subjectEntities = articleRepository.findByAuthorId(authUserDetails.getId());
        return subjectEntities.stream().map(subjectEntity -> convertEntityToDtos(subjectEntity, authUserDetails)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteArticle(String title, AuthUserDetails authUserDetails) {
        String found = articleRepository.findByTitle(title).getAuthor().getId();
        String authfound = authUserDetails.getId();
        if (authfound.equals(found)){
            try {
                ArticleEntity articleToDelete = articleRepository.findByTitle(title);
                logger.info("Article ID Deleted: " + articleToDelete.getId());
                articleRepository.delete(articleToDelete);
            } catch (AppException aex) {
                throw new ResponseStatusException(
                        aex.getError().getStatus().value(), aex.getError().getMessage(), aex);
            }
        }else if (authfound != found){
            throw new AppException(Error.ARTICLE_NOT_FOUND);
        }

    }

    @Transactional
    @Override
    public ArticleDto updateArticle(String title, ArticleDto.Update article, AuthUserDetails authUserDetails) {
        String found = articleRepository.findByTitle(title).getAuthor().getId();
        String authfound = authUserDetails.getId();
        ArticleEntity foundArticle = articleRepository.findByTitle(title);

        if (authfound.equals(found)){

            if (article.getTitle() != null) {
                foundArticle.setTitle(article.getTitle());
            }

            if (article.getDescription() != null){
                foundArticle.setDescription(article.getDescription());
            }
        }
        articleRepository.save(foundArticle);

        return convertEntityToDto(foundArticle);

//        if (article.getTitle() != null) {
//            found.setTitle(article.getTitle());
//        }
//
//        if (article.getDescription() != null) {
//            found.setDescription(article.getDescription());
//        }


    }

    @Override
    public List<ArticleDto> getAllArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAllArticles();
        return articleEntities.stream().map(articleEntity -> convertEntityToDto(articleEntity)).collect(Collectors.toList());
    }

    private ArticleDto convertEntityToDto(ArticleEntity entity) {

        return ArticleDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    private ArticleDto convertEntityToDtos(ArticleEntity entity, AuthUserDetails authUserDetails) {

        UserEntity author = UserEntity.builder()
                .id(String.valueOf(authUserDetails.getId()))
                .email(authUserDetails.getEmail())
                .build();

        return ArticleDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .author(author)
                .build();
    }



}
