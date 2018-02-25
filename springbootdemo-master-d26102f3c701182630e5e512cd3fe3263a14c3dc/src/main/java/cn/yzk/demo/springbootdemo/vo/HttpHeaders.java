package cn.yzk.demo.springbootdemo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yang on 2/16/18.
 */
@Getter
@Setter
@ToString
public class HttpHeaders {
    private String userId;
    private String platform;
    private String app;
    private String ip;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
