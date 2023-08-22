package com.springboot.crud.mysqlspring.domain.article.service;

import com.springboot.crud.mysqlspring.domain.article.dto.ArticleDto;
import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import com.springboot.crud.mysqlspring.domain.article.repository.ArticleRepository;
import com.springboot.crud.mysqlspring.domain.user.entity.UserEntity;
import com.springboot.crud.mysqlspring.domain.user.repository.UserRepository;
import com.springboot.crud.mysqlspring.security.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private final ArticleRepository articleRepository;

    @Autowired
    private  final UserRepository userRepository;

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
    public void deleteArticle(String title) {
        ArticleEntity found = articleRepository.findByTitle(title);
        articleRepository.delete(found);
    }

    @Transactional
    @Override
    public ArticleDto updateArticle(String title, ArticleDto.Update article) {
        ArticleEntity found = articleRepository.findByTitle(title);

        if (article.getTitle() != null) {
            found.setTitle(article.getTitle());
        }

        if (article.getDescription() != null) {
            found.setDescription(article.getDescription());
        }

//        if (article.getAuthor() != null) {
//            found.setAuthor(article.getAuthor());
//        }

        articleRepository.save(found);

        //return getArticle(title);

        return convertEntityToDto(found);
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
