package com.spring.springdemo;


import com.spring.springdemo.User;
import mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdemoApplicationTests {
   // @Autowired
    public UserMapper userMapper;

    @Test
    @Transactional
    public void test(){

        userMapper.insert("cailh", "123456", 1);
        userMapper.insert("admin","147369",2);
        userMapper.insert("root","789456",3);
        User u = userMapper.findUserByID(1);
        User u1= userMapper.findUserByID(2);
        Assert.assertEquals("admin",u1.getName());
        Assert.assertEquals("cailh", u.getName());
        u1.setName("master");
        u1.setPassword("456789");
        userMapper.update(u1);
        Assert.assertEquals("master",u1.getName());
    }
}
