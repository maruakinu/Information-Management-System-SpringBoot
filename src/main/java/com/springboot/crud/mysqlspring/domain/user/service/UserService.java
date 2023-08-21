package com.springboot.crud.mysqlspring.domain.user.service;

import com.springboot.crud.mysqlspring.domain.user.dto.UserDto;

public interface UserService {

    UserDto registration(final UserDto.Registration registration);

    UserDto login(final UserDto.Login login);
}
