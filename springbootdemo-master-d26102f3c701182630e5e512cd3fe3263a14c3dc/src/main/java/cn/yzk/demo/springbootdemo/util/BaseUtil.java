package cn.yzk.demo.springbootdemo.util;

import java.util.UUID;

/**
 * Created by yang on 2/16/18.
 */
public class BaseUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
