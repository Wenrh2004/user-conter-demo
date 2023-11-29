package com.qitsoftwarestudio.userconterdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qitsoftwarestudio.userconterdemo.mapper")
public class UserConterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConterDemoApplication.class, args);
    }

}
