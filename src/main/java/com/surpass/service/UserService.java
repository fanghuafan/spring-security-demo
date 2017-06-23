package com.surpass.service;

import com.surpass.entity.User;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserService {

    /**
     * 方便测试用，实际开发需要根据具体业务从数据库获取用户
     */
    public User getUser(@NotNull String userName, @NotNull String password) {
        if (userName.equals("admin") && password.equals("123456")) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setType(1);

            return user;
        } else if (userName.equals("user") && password.equals("123456")) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setType(2);

            return user;
        } else {
            return null;
        }
    }

    /**
     * 同上
     */
    public User findByUserName(@NotNull String userName) {
        if (userName.equals("admin")) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword("123456");
            user.setType(1);

            return user;
        } else if (userName.equals("user")) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword("123456");
            user.setType(2);

            return user;
        } else {
            return null;
        }
    }
}
