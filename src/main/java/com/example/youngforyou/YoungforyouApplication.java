package com.example.youngforyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.**.dao"})
public class YoungforyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoungforyouApplication.class, args);
    }

}
