package com.example.shiro.controller;

import com.example.shiro.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    public String login(UserDto user) {
        //1：获取subject
        Subject subject = SecurityUtils.getSubject();
        //2:封装用户账号和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        //3:执行登录方法
        try {
            subject.login(usernamePasswordToken);
            //登录成功
            //成功后跳转到
            //return "redirect:/test";
            return "登录成功";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败用户名不存在
            return "登录失败：用户名不存在";
        } catch (IncorrectCredentialsException e) {
            //登录失败密码错误
            return "登录失败：密码错误";
        }
    }

    @RequestMapping("/getLoginUser")
    public String getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal().toString();
    }
}


