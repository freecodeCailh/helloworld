package com.cailh.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
项目启动入口，所有配置包的跟路径
*/
@SpringBootApplication
@ComponentScan(basePackages = "com.cailh")
public class Entry {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Entry.class, args);
        /*
        * 数据库一直报错
        * 报错提示：Unable to create initial connections of pool.
        *java.sql.SQLException: Access denied for user 'cailh'@'localhost' (using password: YES)
        */
    }
}
