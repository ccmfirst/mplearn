package com.first.demo.controller;

import com.first.demo.dao.UserMapper;
import com.first.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mplearn")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/listuser")
    private List<User> listUser(){
        List<User> list = userMapper.selectList(null);
        return list;
    }
}
