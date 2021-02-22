package com.first.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.first.demo.entity.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    int saveData(Map<String, Object> dataMap);
}
