package com.tlias;

import java.util.Date;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTest {
    /*
     * 生成jwt令牌
     */
    @Test
    public void testGenerateJwt() {
        String jwt = Jwts.builder()
            .signWith(Keys.hmacShaKeyFor("secretKey1111111111111111111111111111".getBytes()), Jwts.SIG.HS256) // 指定秘钥，加密算法
            .claims()
                .add("id", 1) // 添加自定义信息
                .add("name", "a") // 添加自定义信息
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过明时间
                .and()
            .compact(); // 生成令牌
        System.out.println(jwt);
    }

    /*
     * 解析jwt令牌
     */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibmFtZSI6ImEiLCJleHAiOjE3NDIxMzYzMTZ9.Qy1yAwvMkcGuWMAzd4Tpq5gEc8Pgu12TqFcdFKGoUkk";
        String payload = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor("secretKey1111111111111111111111111111".getBytes())) //指定秘钥
            .build()
            .parseSignedClaims(token) // 解析令牌
            .getPayload().toString(); // 茯取自定义信息
        System.out.println(payload);
    }
}
