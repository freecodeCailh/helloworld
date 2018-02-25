package cn.yzk.demo.springbootdemo.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by yang on 2/16/18.
 * user实体类
 */
@Getter
@Setter
@ToString
public class BookReq implements Serializable{
    private static final long serialVersionUID= 3221700752972709820L;
    @NotNull(groups = {update.class,delete.class,info.class})
    private String uuid;
    @NotNull(groups = {create.class})
    private String name;
    @NotNull(groups = {create.class})
    private String desc;
    @NotNull(groups = {create.class})
    private String path;
    private Integer page = 1;
    private Integer limit = 30;

    public interface create{}
    public interface update{}
    public interface delete{}
    public interface info{}
    public interface list{}
    public BookReq(String id,String name ,String desc)
    {
        super();
        this.uuid=id;
        this.name=name;
        this.desc=desc;
    }
    public  BookReq(){}

}
