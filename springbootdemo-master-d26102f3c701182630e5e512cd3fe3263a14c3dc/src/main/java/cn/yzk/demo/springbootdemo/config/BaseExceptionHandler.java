package cn.yzk.demo.springbootdemo.config;

import cn.yzk.demo.springbootdemo.dto.response.JsonResult;
import cn.yzk.demo.springbootdemo.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yang on 2/16/18.
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class BaseExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public JsonResult ServiceExceptionHandler(Exception e){
        e.printStackTrace();
        return JsonResult.failed(e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public JsonResult ExceptionHandler(Exception e){
        e.printStackTrace();
        return JsonResult.failed("发生未知错误");
    }
}
