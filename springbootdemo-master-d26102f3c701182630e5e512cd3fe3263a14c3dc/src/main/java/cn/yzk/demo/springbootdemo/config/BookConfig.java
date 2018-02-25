package cn.yzk.demo.springbootdemo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yang on 2/16/18.
 * 获取配置信息
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "bookConfig")
public class BookConfig {
    private String savePath;

    public String getSavePath()
    {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
