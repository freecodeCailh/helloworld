package com.cailh.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/*
项目启动入口，所有配置包的跟路径
*/
@SpringBootApplication
@ComponentScan(basePackages = "com.cailh")
@EnableAutoConfiguration(exclude={
        JpaRepositoriesAutoConfiguration.class//禁止springboot自动加载持久化bean
})
public class Entry {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Entry.class, args);

    }


}
