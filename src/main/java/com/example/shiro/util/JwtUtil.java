package com.example.shiro.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

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
}
