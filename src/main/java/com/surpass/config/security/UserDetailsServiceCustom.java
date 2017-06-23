package com.surpass.config.security;

import com.surpass.entity.User;
import com.surpass.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 重写loadUserByUsername
 * Created by surpass.wei@gmail.com on 2016/12/16.
 */
@Component
public class UserDetailsServiceCustom implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceCustom.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailsBean userDetailsBean;

        User user = userService.findByUserName(username);

        if (user==null) {
            logger.warn("该用户不存在：" + username);
            throw new UsernameNotFoundException("该用户不存在：" + username);
        } else {
            userDetailsBean = new UserDetailsBean(user);
        }

        return userDetailsBean;
    }
}
