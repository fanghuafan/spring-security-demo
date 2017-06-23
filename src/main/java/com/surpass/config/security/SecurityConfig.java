package com.surpass.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * security核心配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomSuccessHandler customSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    //.antMatchers("/public/**","/").permitAll() // 配置那些URL可以直接访问
                    //.antMatchers("/index").permitAll()//.hasRole("TEACHER") // 配置那些URL需要何种权限
                    .anyRequest().authenticated()   //  任何尚未匹配的的URL地址只需要对用户进行权限验证
                .and()
                    .formLogin()
                    .successHandler(customSuccessHandler)   //  自定义登录成功后的处理
                    .failureUrl("/login?error=true")
                    //.defaultSuccessUrl("/home")   //  登录成功后跳转到哪个URL
                .and()
                    .logout().logoutSuccessUrl("/") //  登出成功后跳转到哪个URL
                .and()
                    .rememberMe().tokenValiditySeconds(60*60*24*7); //  开启“记住我”功能，并配置有效时间
        http.logout();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //  将验证过程交给自定义的授权认证类
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceCustom();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProviderCustom();
    }
}
