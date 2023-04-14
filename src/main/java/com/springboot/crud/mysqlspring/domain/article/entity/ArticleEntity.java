package com.springboot.crud.mysqlspring.domain.article.entity;



import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;


    @Builder
    public ArticleEntity(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}

