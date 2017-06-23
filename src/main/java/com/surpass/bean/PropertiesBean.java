package com.surpass.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件的接收bean
 * Created by surpass.wei@gmail.com on 2017/1/16.
 */
@Component
@ConfigurationProperties
public class PropertiesBean {
    private Map<String, String[]> role = new HashMap<>();   //  接收role里面的属性值

    public Map<String, String[]> getRole() {
        return role;
    }
}
