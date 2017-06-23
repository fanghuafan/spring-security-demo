package com.surpass.web;

import com.surpass.config.security.UserDetailsBean;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Authentication authentication) {
        UserDetailsBean user = (UserDetailsBean) authentication.getPrincipal();
        return "你好:" + user.getUsername();
    }
}
