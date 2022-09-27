package com.example.shiro.service;

import com.example.shiro.dto.UserDto;

public interface IUserService {

    UserDto findById(Long id);

}
