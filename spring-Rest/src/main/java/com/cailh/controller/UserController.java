package com.cailh.controller;


import com.cailh.config.User;
import com.cailh.respositoty.UserRespositoty;
import com.cailh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* User 控制层
*/
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

//    @RequestMapping(value = "/add")
//    public void userAdd(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("password") String password)
//    {
        //添加人员
//        User user=new User();
//        user.setId(id);
//        user.setName(name);
//        user.setPassword(password);
//        UserRespositoty.save(user);
//    }
//    @RequestMapping(value = "/delete")
//    public  void  userDelete(@PathVariable("id") int id)
 //   {
//        //删除人员
//        UserRespositoty.delete(Long.valueOf(id));
//   }
    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        //展示人员
        User user = userService.findUserByName(name);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
        else return "null";
    }
}
