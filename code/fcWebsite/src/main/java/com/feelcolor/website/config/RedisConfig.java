package com.feelcolor.website.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.Charset;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {
    private String host;
    private Integer port;
    private String password;
    private Integer dataBase;
    private Integer maxTotal;
    private Integer maxIdle;
    private boolean testOnBorrow;
    private long maxWaitMillis;
    private Integer numTestsPerEvictionRun;
    private long softMinEvictableIdleTimeMillis;
    private long timeBetweenEvictionRunsMillis;

    @Bean(destroyMethod = "destroy")
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数, 默认8个
        poolConfig.setMaxTotal(maxTotal);
        // 最大空闲连接数, 默认8个
        poolConfig.setMaxIdle(maxIdle);
        // 在获取连接的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        // 每次逐出检查时 逐出的最大数目
        poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数
        poolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
        // 逐出扫描的时间间隔(毫秒)
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisConnectionFactory.setPoolConfig(poolConfig);
        jedisConnectionFactory.setDatabase(dataBase);
        return jedisConnectionFactory;
    }

    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<K, V> redisTemplate = new RedisTemplate<K, V>();
        redisTemplate.setDefaultSerializer(new StringRedisSerializer(Charset.forName("UTF-8")));
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

}
