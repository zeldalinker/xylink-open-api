package com.xylink.wechat.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-23
 */
@EnableCaching
@Configuration
public class CaffeineCacheConfig {

    /**
     * 创建基于Caffeine的Cache Manager
     */
    @Bean("localCacheManager")
    public CacheManager localCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().recordStats()
                .expireAfterWrite(10000, TimeUnit.SECONDS)
                .maximumSize(3));
        return cacheManager;
    }

}
