package com.cailh.controller;


import com.cailh.config.User;
import com.cailh.respositoty.userRepositoty;
import com.cailh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/*
* User 控制层
*/
@RestController
@RequestMapping(value = "/user")
public  class UserController {

    public UserService userService;


    private userRepositoty userRepositoty;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index()
    {
        return "***********HELLO*************";
    }

    @RequestMapping(value = "/updataUser",method = RequestMethod.POST)
    @ResponseBody
    public String userUpdata(@RequestBody User userNew)
    {
        //更新人员
        if(userNew != null){
        User userold = userRepositoty.findByUserId(userNew.getId());
        userold.setName(userNew.getName());
        userRepositoty.save(userold);
        return "更新成功"+userNew.getId()+userold.getName();
        }
        return "空对象";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void userAdd(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("password") String password)
    {
        //添加人员
        User userin=new User(id,name,password);
        userRepositoty.save(userin);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  String   userDelete( int id)
    {
        //删除人员
        userRepositoty.delete(id);
        return "删除成功";
    }


    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String showByName(@RequestParam(value = "name")String name)
    {
        //查询人员
        User user = userService.findUserByName(name);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
        else return "无人员";
    }
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String showById(@RequestParam(value = "id")int id)
    {
        //查询人员
        User user = userService.findUserById(id);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
        else return "无人员";
    }
}
