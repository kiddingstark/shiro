package com.example.shiro.service.impl;

import com.example.shiro.dto.UserDto;
import com.example.shiro.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author Ricky
 * @Date 2022/3/24 0:09
 */

@Service
public class IUserServiceImpl implements IUserService {

    @Override
    public UserDto findById(Long id) {
        UserDto user = UserDto.builder()
                .id(id)
                .userName("Ricky")
                .password("123456")
                .build();
        return user;
    }

}
