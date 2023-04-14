package com.springboot.crud.mysqlspring.domain.article.repository;



import com.springboot.crud.mysqlspring.domain.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {


}
