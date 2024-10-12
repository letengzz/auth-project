package com.hjc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author hjc
 */
@SpringBootApplication
@MapperScan("com.hjc.mapper")
public class AuthSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthSystemApplication.class, args);
    }
}
