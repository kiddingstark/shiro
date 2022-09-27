package com.example.shiro.dto;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: zhengruihong
 * @description
 * @date: 2022/9/27 17:41
 */

@Data
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
