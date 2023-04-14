package com.springboot.crud.mysqlspring.domain.article.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
    @NonNull
    private String author;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleArticle<T> {
        private T article;
    }

}
