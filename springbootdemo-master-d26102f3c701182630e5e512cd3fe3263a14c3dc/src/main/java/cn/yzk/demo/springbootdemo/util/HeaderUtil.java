package cn.yzk.demo.springbootdemo.util;

import cn.yzk.demo.springbootdemo.exception.ServiceException;
import cn.yzk.demo.springbootdemo.vo.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yang on 2/16/18.
 * 请求头工具方法
 */
public class HeaderUtil {

    public static HttpHeaders getHeaders(HttpServletRequest request,boolean check){
        if (check && !checkHeader(request)){
            throw new ServiceException("验证不通过");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setApp(request.getHeader("app"));
        headers.setPlatform(request.getHeader("platform"));
        headers.setUserId(request.getHeader("userId"));
        headers.setIp(getIp(request));
        return headers;
    }

    public static boolean checkHeader(HttpServletRequest request){
        return request.getHeader("userId") != null
                && request.getHeader("platform") != null
                && request.getHeader("app") != null
                ;
    }

    public static String getIp(HttpServletRequest request){
        if (request.getHeader("X-Forwarded-For") != null){
            return request.getHeader("X-Forwarded-For").split(",")[0];
        }
        if (request.getHeader("X-Real-IP") != null) {
            return request.getHeader("X-Real-IP");
        }
        return request.getRemoteAddr();
    }


}
