package cn.yzk.demo.springbootdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * Created by yang on 2/16/18.
 */

@SpringBootApplication
public class DemoApplication  {
    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class.getName());


    @Autowired
    private StringRedisTemplate template;

    public static void main(String[] args) throws InterruptedException{
            SpringApplication.run(DemoApplication.class,args);

    }



}
