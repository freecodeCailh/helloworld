package cn.yzk.demo.springbootdemo.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yang on 2/16/18.
 */
@Getter
@Setter
@ToString
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResult success(Object data){
        return success("",data);
    }

    public static JsonResult success(String msg){
        return success(msg,null);
    }

    public static JsonResult success(String msg,Object data){
        return build(1,msg,data);
    }

    public static JsonResult failed(String msg){
        return failed(msg,null);
    }
    public static JsonResult failed(Object data){
        return failed("",data);
    }
    public static JsonResult failed(String msg,Object data){
        return build(0,msg,data);
    }

    public static JsonResult build(int code,String msg,Object data)
    {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


}
