package com.feelcolor.website.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源,spring boot 会自动将dataSource注入到sessionfactory 与 template等中
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableConfigurationProperties(DruidConfig.class) // 注入配置文件
@Slf4j
public class DataSourceConfig {
    @Resource
    DruidConfig druidConfig;

    @Bean
    public DataSource dataSource() {
        log.info("==============================创建数据源==============================");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidConfig.getDirverClassName());
        dataSource.setUrl(druidConfig.getUrl());
        dataSource.setUsername(druidConfig.getUsername());
        dataSource.setPassword(druidConfig.getPassword());
        dataSource.setInitialSize(druidConfig.getInitialSize());
        dataSource.setMinIdle(druidConfig.getMinIdle());
        dataSource.setMaxActive(druidConfig.getMaxActive());
        dataSource.setTestOnBorrow(druidConfig.isTestOnBorrow());
        return dataSource;
    }
}
