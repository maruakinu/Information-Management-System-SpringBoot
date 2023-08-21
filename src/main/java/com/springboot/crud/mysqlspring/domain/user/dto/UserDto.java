package com.springboot.crud.mysqlspring.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    protected String id;

    @NotNull
    private String username;

    @NotNull
    private String email;
    @NotNull
    private String password;

    private String token;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonTypeName("user")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Registration {

        @NotNull
        private String username;

        @NotNull
        private String email;

        @NotNull
        private String password;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @JsonTypeName("user")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Login {
        @NotNull
        private String email;

        @NotBlank
        private String password;
    }


}
