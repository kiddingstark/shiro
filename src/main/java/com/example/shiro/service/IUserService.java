package com.example.shiro.service;

import com.example.shiro.dto.UserDto;

public interface IUserService {

    UserDto findById(String id);

    UserDto findByUserName(String username);
}
