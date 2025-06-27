package com.liu.covid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 配置类，用于设置全局跨域访问的规则
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    // 重写 addCorsMappings 方法，配置跨域访问规则
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径的跨域请求
        registry.addMapping("/**")
                // 允许的来源，"*" 表示任意来源
                .allowedOrigins("*")
                // 允许的 HTTP 方法
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                // 是否允许携带凭据（如 Cookie）
                .allowCredentials(true)
                // 预检请求的缓存时间，单位为秒
                .maxAge(3600)
                // 允许的请求头，"*" 表示任意请求头
                .allowedHeaders("*");
    }
}

