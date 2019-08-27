package com.example.springbootSample.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Component
@ConfigurationProperties(prefix = "sysConfig") //读取properties配置文件中的属性值(基于类型安全)
@PropertySource("classpath:sysConfig.properties")
@Data
public class SysConfig {
    private String code;
    private String name;
    private String desc;
}
