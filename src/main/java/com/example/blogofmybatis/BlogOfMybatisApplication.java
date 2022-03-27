package com.example.blogofmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.example.blogofmybatis.dao")
@SpringBootApplication
public class BlogOfMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogOfMybatisApplication.class, args);
    }

}
