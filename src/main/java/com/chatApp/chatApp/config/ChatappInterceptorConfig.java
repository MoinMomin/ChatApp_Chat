package com.chatApp.chatApp.config;

import com.chatApp.chatApp.interceptor.ChatappInterceptor;
import com.chatApp.chatApp.mapper.BearerTokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class ChatappInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    ChatappInterceptor chatappInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(chatappInterceptor).addPathPatterns("/**").excludePathPatterns("/public/**");
    }

    @Bean
    public ChatappInterceptor bearerTokenInterceptor() {
        return new ChatappInterceptor(bearerTokenWrapper());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BearerTokenWrapper bearerTokenWrapper() {
        return new BearerTokenWrapper();
    }
}
