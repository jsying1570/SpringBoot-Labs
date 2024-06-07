package com.example;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hc.mapper")
public class XAGoodApp {

    public static void main(String[] args) {

        SpringApplication.run(XAGoodApp.class, args);
    }
}
