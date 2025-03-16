package com.tlias.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@WebFilter("/*") // 拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    /*
     * 初始化过滤器，web服务器启动时执行，只执行一次
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init初始化...");
    }

    /*
     * 拦截到请求之后，执行，会执行多次
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("拦截到请求...");
        // 放行
        chain.doFilter(request, response);
    }

    // 销段方法，web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
        log.info("destroy销毁方法...");
    }

}
