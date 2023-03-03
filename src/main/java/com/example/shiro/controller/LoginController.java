package com.example.shiro.controller;

import com.example.shiro.dto.UserDto;
import com.example.shiro.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author Ricky
 * @Date 2022/3/23 23:59
 */

@Slf4j
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestBody UserDto user) {
        //1、假设账号密码认证正确
        //2、返回token
        return JwtUtil.generateToken(user.getId());
    }

}


