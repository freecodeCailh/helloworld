package com.cailh.service;


import com.cailh.config.User;
import com.cailh.respositoty.userRepositoty;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    public userRepositoty userRepositoty;

    public User findUserByName(String name){
        User user = null;
        try{
            user = userRepositoty.findByUserName(name);
        }catch (Exception e){}
        return user;
    }
    public User findUserById(int id)
    {
        User user = null;
        try{
            user = userRepositoty.findByUserId(id);
        }catch (Exception e){}
        return user;
    }
}
