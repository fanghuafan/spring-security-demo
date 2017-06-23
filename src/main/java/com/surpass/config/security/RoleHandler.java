package com.surpass.config.security;

import com.surpass.bean.PropertiesBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色权限管理以及初始化
 * 目前是读取配置文件来初始化用户权限
 */
@Component
public class RoleHandler {

    @Resource
    private PropertiesBean propertiesBean;

    private final List<Role> roleList = new ArrayList<>();

    /**
     * 根据application.yml配置文件中的角色权限控制来初始化roles
     */
    private void init() {

        Map<String, String[]> roleMap = propertiesBean.getRole();   //  配置文件中的所有角色
        List<String> privilegeList;
        Role role;
        for (String key : roleMap.keySet()) {
            String[] privilegeArray = roleMap.get(key);
            privilegeList = Arrays.asList(privilegeArray);
            role = new Role(key, privilegeList);
            roleList.add(role);
        }

    }

    Role getRole(String roleName) {
        if (roleList.isEmpty()) {
            this.init();
        }

        if (roleName == null || roleName.isEmpty()) {
            return null;
        }

        Role role = new Role();
        for (Role temp_role : roleList) {
            if (temp_role.getName().compareToIgnoreCase(roleName) == 0) {
                role = temp_role;
            }
        }

        return role;
    }
}
