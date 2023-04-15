package com.springboot.crud.mysqlspring.domain.article.repository;



import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity findByTitle(@Param("title") String title);

}
