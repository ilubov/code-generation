package com.i.lubov.util;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private RedissonClient redissonClient;

    public static final long NOT_EXPIRE = -1L;

    private static RedisUtils instance = null;

    public RedisUtils() {
        instance = this;
    }

    public static <T> void set(String key, T value, long expire) {
        instance.redissonClient.getBucket(key).set(value, expire, TimeUnit.SECONDS);
    }

    public static <T> void set(String key, T value) {
        instance.redissonClient.getBucket(key).set(value);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return (T) instance.redissonClient.getBucket(key).get();
    }

    public static void delete(String key) {
        instance.redissonClient.getBucket(key).delete();
    }
}
