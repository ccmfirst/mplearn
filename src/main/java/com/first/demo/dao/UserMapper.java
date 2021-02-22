package com.first.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.first.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    int saveData(Map<String, Object> dataMap);
    void importUser(@Param(value="map") Map<String,Object> map, @Param(value="table_name") String tableName);
}
