package com.first.demo;

import com.first.demo.dao.UserMapper;
import com.first.demo.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Ignore
    public void select(){
        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("张松");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
        user.setEmail("ccc@qq.com");
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }
}
