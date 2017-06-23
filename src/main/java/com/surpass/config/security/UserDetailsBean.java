package com.surpass.config.security;

import com.surpass.entity.User;
import com.surpass.util.SpringContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 基于User包装一个UserDetailsBean(用于security权限认证)
 * 主要为了实现 getAuthorities()
 */
public class UserDetailsBean extends User implements UserDetails {

    public UserDetailsBean(User user) {
        BeanUtils.copyProperties(user,this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //  获取用户的角色/权限信息
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        RoleHandler roleHandler = SpringContextUtil.getApplicationContext().getBean(RoleHandler.class);

        if (roleHandler == null) {
            return null;
        }

        // 根据用户的类型, 构建用户对应的Role
        String roleName;
        if (this.getType()==1) {
            roleName = "ADMIN";
        } else {
            roleName = "USER";
        }
        Role role = roleHandler.getRole(roleName);

        //  循环角色的权限信息, 构建authorities
        for (String privilege : role.getPrivileges()) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        //  账户是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //  账户是否未锁定
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 凭证是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        //  是否有效
        return true;
    }
}
