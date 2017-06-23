package com.surpass.config.security;

import com.surpass.entity.User;
import com.surpass.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义的授权认证类
 */
@Component
public class AuthenticationProviderCustom implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(AuthenticationProviderCustom.class);

    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String userName = token.getName();
        String password = token.getCredentials().toString();
        User user = userService.getUser(userName, password);

        UserDetailsBean userDetailsBean;
        if(user != null) {
            userDetailsBean = new UserDetailsBean(user);
        } else {
            logger.error("用户名或密码错误");
            throw new BadCredentialsException("用户名或密码错误");
        }

        return new UsernamePasswordAuthenticationToken(userDetailsBean, password, userDetailsBean.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
