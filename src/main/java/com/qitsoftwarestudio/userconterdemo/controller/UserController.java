package com.qitsoftwarestudio.userconterdemo.controller;

import com.qitsoftwarestudio.userconterdemo.model.request.UserRegisterRequest;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.representer.BaseRepresenter;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public String userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new RuntimeException("user information is null");
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return String.format("user information is : %s", result);
    }
}
