package cn.yzk.demo.springbootdemo.web;

import cn.yzk.demo.springbootdemo.dto.request.BookReq;
import cn.yzk.demo.springbootdemo.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Redis控制类
 * */
@RestController
@RequestMapping("/user")
public class BookrepController {
    @Autowired
    private UserRedisService userRedisService;
    /**
     * @Title: UserController
     * @Description: 初始化redis数据
     * 添加一个新的user
     */
    @RequestMapping("/initRedisdata")
    @ResponseBody
    public String initRedisData(){
        userRedisService.redisInitData();
        return "success";
    }

    /**
     * 查询user
     */
    @RequestMapping("/getUserRedisByKey/{uuid}")
    @ResponseBody
    public Map<String,Object> getUserRedisByLoginName(@PathVariable String uuid){
        Map<String,Object> result = new HashMap<String, Object>();
        BookReq user = userRedisService.getUserRedis(uuid);
        //Assert.notNull(user);
        result.put("key:", user.getUuid());
        result.put("loginName:", user.getName());
        result.put("desc:",user.getDesc());
        return result;
    }
}
