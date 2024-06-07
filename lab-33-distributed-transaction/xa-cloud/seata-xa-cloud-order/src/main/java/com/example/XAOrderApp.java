package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hc.mapper")
@SpringBootApplication
public class XAOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(XAOrderApp.class, args);
    }
}
