package com.first.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.first.demo.dao.UserMapper;
import com.first.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Resource
    private  UserMapper userMapper;

    public void saveData(Map<String, Object> dataMap){
        this.userMapper.saveData(dataMap);
    }
}
