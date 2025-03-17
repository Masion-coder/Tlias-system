package com.tlias.filter;

import java.io.IOException;

import com.tlias.util.JwtUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@WebFilter("/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 1.获取请求路径
        StringBuffer requestURL = req.getRequestURL(); // /smbms/user/login

        // 2.判断是否需要拦截
        if (requestURL.toString().contains("login")) {
            log.info("登录请求，放行");
            chain.doFilter(request, response);
            return;
        }

        // 3.获取请求头中的token
        String token = req.getHeader("token");

        // 4.判断token是否存在，不存在则返回401状态码
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5.如果存在，校验token，如果不合法，返回401状态码
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 6.如果合法，放行
        log.info("令牌合法，放行");
        chain.doFilter(request, response);
    }
    
}
