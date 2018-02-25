package cn.yzk.demo.springbootdemo.service;



import java.util.ArrayList;
import java.util.List;

import cn.yzk.demo.springbootdemo.config.RedisConfig;
import cn.yzk.demo.springbootdemo.dto.request.BookReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;


@Service
@ContextConfiguration(classes = {RedisConfig.class,BookReq.class} )
public class UserRedisService
{
    private Logger logger = LoggerFactory.getLogger(UserRedisService.class);

    @Autowired
    private RedisService userRedis;


    /**
     *存储新的user
     **/
    public void redisInitData(){

        BookReq user =new BookReq();
        user.setName("管理员REDIS");
        List<BookReq> userList = new ArrayList<BookReq>();
        user.setUuid("adminRedis");
        user.setDesc("uudesc");
        userList.add(user);
        //userRedis.deleteByKey(user.getUuid()+":userByLoginName:"+user.getName());
        userRedis.addUser(user.getUuid()+":userByLoginName:"+user.getName(),3600L,user);
        logger.info("key:" + user.getUuid()+"    userByLoginName:"+user.getName()+"    desc:"+user.getDesc());
    }

    /**
     * 根据key查找user
     * */
    public BookReq getUserRedis(String uuid){
        BookReq user = userRedis.getUserByKey(uuid);
        Assert.notNull(user,"用户为空！");
        logger.info("===user=== name:{},loginName: {},departmentName:{}, roleName:{}", user.getName(),user.getUuid(),user.getDesc());
        return user;
    }
}
