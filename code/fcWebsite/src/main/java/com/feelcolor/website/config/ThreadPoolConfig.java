package com.feelcolor.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10); //核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(50);  //最大线程数
        threadPoolTaskExecutor.setQueueCapacity(1000);  //队列大小
        threadPoolTaskExecutor.setKeepAliveSeconds(300);   //超过多长时间进行线程回收
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
