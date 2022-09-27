package com.example.shiro.controller;

import com.example.shiro.dto.UserDto;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        Date expireDate = new Date(new Date().getTime() + 600000 * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getId() + "")
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

}


