package com.store.user.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private Integer role;  // 1-管理员 2-普通用户
    private Integer status; // 0-禁用 1-正常
    private Date createTime;
    private Date updateTime;
}
