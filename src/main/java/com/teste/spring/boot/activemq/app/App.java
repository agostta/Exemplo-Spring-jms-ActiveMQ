package com.teste.spring.boot.activemq.app;

import org.springframework.boot.SpringApplication;

import com.teste.spring.boot.activemq.config.Config;

public class App {

    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }
}
