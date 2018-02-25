package cn.yzk.demo.springbootdemo.service;


import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.yzk.demo.springbootdemo.dto.request.BookReq;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * Redis业务处理类
 * */
@Repository
public class RedisService
{
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 添加新的User
     * */
    public void addUser(String key,Long time,BookReq user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(user),time,TimeUnit.MINUTES);
    }

    public void addUserList(String key,Long time,List<BookReq> userList){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(userList),time,TimeUnit.MINUTES);
    }


    /**
     * 通过key查找
     * */
    public BookReq getUserByKey(String key){
        Gson gson = new Gson();
        BookReq user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotEmpty(userJson)){
            user =  gson.fromJson(userJson, BookReq.class);
        }
        return user;
    }

    public List<BookReq> getUserListByKey(String key){
        Gson gson = new Gson();
        List<BookReq> userList = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotEmpty(userJson)){
            userList =  gson.fromJson(userJson, new TypeToken<List<BookReq>>(){}.getType()    );
        }
        return userList;
    }

    /**
     * 通过key删除
     * */
    public void deleteByKey(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
