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
public class BookResp {
    private String uuid;
    private String name;
    private String desc;
    private Integer wordNum;

}
