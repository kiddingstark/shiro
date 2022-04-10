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
    public UserDto findById(String id) {
        UserDto user = new UserDto();
        user.setId(id);
        user.setUserName("Ricky");
        user.setPassword("123456");
        return user;
    }

    @Override
    public UserDto findByUserName(String username) {
        UserDto user = new UserDto();
        user.setUserName(username);
        user.setPassword("123456");
        return user;
    }
}
