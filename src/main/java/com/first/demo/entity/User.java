package com.first.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName("user")
public class User {
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;
}
