package com.moyu.oauth2.client.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.moyu.oauth2.client.mapper")
@Configuration
public class MybatisPlusConfig {
}
