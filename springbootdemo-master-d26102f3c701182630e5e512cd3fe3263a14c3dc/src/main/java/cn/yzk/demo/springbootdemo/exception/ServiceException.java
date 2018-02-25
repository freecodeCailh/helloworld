package cn.yzk.demo.springbootdemo.exception;

/**
 * Created by yang on 2/16/18.
 * 基础业务异常
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String msg) {
        super("业务异常： " + msg);
    }
}
