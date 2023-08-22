package com.springboot.crud.mysqlspring.domain.article.entity;



import com.springboot.crud.mysqlspring.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity author;


    @Builder
    public ArticleEntity(String title, String description, UserEntity author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}

