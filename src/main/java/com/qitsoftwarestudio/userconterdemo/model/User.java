package com.qitsoftwarestudio.userconterdemo.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String userAccount;

    private String userPassword;

    private Integer gender;

    private String avatarUrl;

    private String phone;

    private String email;

    private Integer userStatus;

    private Data createTime;

    private Data updateTime;

    private Data deleteTime;

    @TableLogic
    private Integer isDelete;

    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}