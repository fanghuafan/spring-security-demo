package com.surpass.config.security;

import java.util.List;

/**
 * 角色对象
 */
public class Role {
    private String name;    //  名称
    private List<String> privileges;    //  分配的角色/权限

    Role() {

    }

    Role(String name, List<String> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    List<String> getPrivileges() {
        return this.privileges;
    }

    String getName() {
        return name;
    }
}
