package cn.yzk.demo.springbootdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by yang on 2/16/18.
 * 书籍 数据映射实体类
 */
@Getter
@Setter
@ToString
@Entity
public class Book {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 唯一ID
     */
    @Column(columnDefinition = "VARCHAR(32) NOT NULL DEFAULT ''")
    private String uuid;
    /**
     * 用户ID
     */
    @Column(columnDefinition = "VARCHAR(32) NOT NULL DEFAULT ''")
    private String userId;
    /**
     * 书籍名称
     */
    @Column(columnDefinition = "VARCHAR(16) NOT NULL DEFAULT ''")
    private String bookName;
    /**
     * 书籍描述
     */
    @Column(columnDefinition = "VARCHAR(64) NOT NULL DEFAULT ''")
    private String bookDesc;
    /**
     * 书籍文件地址
     */
    @Column(columnDefinition = "VARCHAR(64) NOT NULL DEFAULT ''")
    private String bookPath;
    /**
     * 书籍字数
     */
    @Column(columnDefinition = "INT(8) NOT NULL DEFAULT 0")
    private Integer wordNum;
    /**
     * 创建时间
     */
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()")
    private Date updateTime;


}
