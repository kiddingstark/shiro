package com.example.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhengruihong
 * @description
 * @date: 2022/9/27 18:03
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/getLoginUser")
    public String getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal().toString();
    }
}
