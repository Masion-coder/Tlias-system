package com.tlias.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tlias.util.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // // 1.获取请求路径
        // StringBuffer requestURL = request.getRequestURL(); // /smbms/user/login

        // // 2.判断是否需要拦截
        // if (requestURL.toString().contains("login")) {
        //     log.info("登录请求，放行");
        //     return true;
        // }

        // 3.获取请求头中的token
        String token = request.getHeader("token");

        // 4.判断token是否存在，不存在则返回401状态码
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 5.如果存在，校验token，如果不合法，返回401状态码
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 6.如果合法，放行
        log.info("令牌合法，放行");
        return true;
    }
}
