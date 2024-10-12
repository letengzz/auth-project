package com.hjc.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置pageHelper
 *
 * @author hjc
 */
@Configuration
public class PluginConfig {
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}