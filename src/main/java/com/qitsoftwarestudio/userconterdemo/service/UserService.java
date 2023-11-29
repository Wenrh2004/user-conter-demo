package com.qitsoftwarestudio.userconterdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qitsoftwarestudio.userconterdemo.model.User;

public interface UserService extends IService<User> {
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
