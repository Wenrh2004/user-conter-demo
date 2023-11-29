package com.qitsoftwarestudio.userconterdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qitsoftwarestudio.userconterdemo.mapper.UserMapper;
import com.qitsoftwarestudio.userconterdemo.model.User;
import com.qitsoftwarestudio.userconterdemo.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.hasText;

@Service
@Slf4j
public class UserServiceImpl extends Service<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 检查用户账号、密码是否为空
        if (!StringUtils.hasText(userAccount) || !StringUtils.hasText(userPassword) || !StringUtils.hasText(checkPassword)) {
            throw new BusinessException("user information is null");
        }

        // 检查账号长度是否合规
        if (userAccount.length() < 8) {
            throw new BussinessException("user account length is not format");
        }

        // 检查账户拼写是否合规
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }

        // 检查密码长度是否合规
        if (userPassword.length() < 8) {
            throw new BussinessException("user password length is not format");
        }

        // 检查用户密码和核验密码是否一致
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }

        // To check Whether the account is registered
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "account has been registered");
        }

        // password encrypted
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());

        // Get new user object
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "User creation failed");
        }

        return user.getId();
    }
}
