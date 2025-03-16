package com.tlias.util;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
    private static final String SECRET_KEY = "secretKey1111111111111111111111111111";//2 usages
    private static final long EXPIRATION_TIME = 12 * 6060 * 1000;//12 1 usage

    public static String generateToken(Map<String,Object> claims) {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), Jwts.SIG.HS256) // 指定秘钥，加密算法
            .claims()
                .add(claims) // 添加自定义信息
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过明时间
                .and()
            .compact(); // 生成令牌
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) //指定秘钥
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
