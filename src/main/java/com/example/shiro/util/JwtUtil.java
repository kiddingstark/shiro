package com.example.shiro.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author: zhengruihong
 * @description
 * @date: 2022/9/27 17:48
 */

@Slf4j
public class JwtUtil {

    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("解析token出错");
            return null;
        }
    }

    /**
     * 生成token
     * 如果项目用的是feign的话，可以在feign的拦截器FeignRequestIntecepto中根据userId生成token并放在header中
     */
    public static String generateToken(Long userId) {
        Date expireDate = new Date(new Date().getTime() + 600000 * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, "项目的签名")//只要项目签名没有泄漏就不可能仅凭userId伪造出token
                .compact();
    }
}
