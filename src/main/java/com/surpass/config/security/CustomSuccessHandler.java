package com.surpass.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException, ServletException {
        UserDetailsBean user = (UserDetailsBean) authentication.getPrincipal();

        if (user.getType() == 1) {
            System.out.println("管理员登陆成功");
        } else if (user.getType() == 2) {
            System.out.println("普通用户登陆成功");
        } else {
            System.out.println("错误");
            return;
        }

        redirectStrategy.sendRedirect(request,response,"/index");
    }
}
