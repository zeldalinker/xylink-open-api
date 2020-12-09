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
     * 政务微信缓存
     */
    @Bean("wechatCacheMgr")
    public CacheManager wechatCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().recordStats()
                .expireAfterWrite(7200, TimeUnit.SECONDS)
                .maximumSize(100));
        return cacheManager;
    }

}
