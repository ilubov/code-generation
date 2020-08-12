package com.i.lubov.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = {"redisson.enabled"}, havingValue = "true")
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Value("${redisson.ttl:86400000}")
    private Long ttl;
    @Value("${redisson.maxIdleTime:86400000}")
    private Long maxIdleTime;
    @Value("${redisson.type:1}")
    private Integer type;
    @Value("${redisson.nodes}")
    private String nodes;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(nodes);
        return Redisson.create(config);
    }
}
