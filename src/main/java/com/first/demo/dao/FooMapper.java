package com.first.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FooMapper {

    int insert(Map<String, Object> condition);
}

