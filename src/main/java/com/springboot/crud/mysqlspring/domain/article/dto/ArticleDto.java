package com.springboot.crud.mysqlspring.domain.article.dto;

import com.springboot.crud.mysqlspring.domain.user.entity.UserEntity;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArticleDto {

    @NonNull
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String description;

    private UserEntity author;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleArticle<T> {
        private T article;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleArticle {
        private List<ArticleDto> articles;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String title;
        private String description;
        private String author;
    }

}
