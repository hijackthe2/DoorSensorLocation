package com.example.doorsensor.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CrossDomainFilter", urlPatterns = "/api/*")
@Configuration
@Slf4j
public class CrossDomainFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("CrossDomainFilter initialized");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("CrossDomainFilter operating");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String referer = request.getHeader("Referer");
        String domain = referer != null ? referer.split("//")[1].split("/")[0] : "";
        switch (domain) {
            case "localhost:8080":
            case "localhost:8000":
            case "localhost:80":
                //响应标头指定 指定可以访问资源的URL路径
                response.setHeader("Access-Control-Allow-Origin", "http://" + domain);
                break;
            default:
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }

        //响应标头指定响应访问所述资源时允许的一种或多种方法
        response.setHeader("Access-Controll-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE");
        //设置缓存可以生存的最大秒数
        response.setHeader("Access-Control-Max-Age", "3600");
        //设置受支持请求标头
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, x-requested-with, Content-Type, Accept, token, Authorization");
        //指示的请求的响应是否可以暴露于该页面，当返回true时，表示可以被暴露
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        //应答转化成json格式对象
        response.setContentType("application/json;charset=UTF-8");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {
        log.info("CrossDomainFilter destroyed");
        this.filterConfig = null;
    }
}
