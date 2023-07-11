package com.hcj.handle;

import com.hcj.entity.FastUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * CacheHandle
 *   缓存处理类
 * @author hcj
 * @date 2023-06-14
 */
@Component("cacheHandle")
public class CacheHandle {

    @Autowired
    private CacheManager cacheManager;

    private final String USER_KEY = "users";

    /**
     * 获取缓存对象
    */
    public Cache getUsersCache(){
        // 在ehcache中设置的name要和USER_KEY一致
        Cache cache = cacheManager.getCache(USER_KEY);
        return cache;
    }

    /**
     * 存储用户登录，缓存token
    */
    public void addUsersCache(String key,Object obj){
        // 获取缓存对象
        Cache cache = getUsersCache();
        // 存入ehcache中
        cache.put(key,obj);
    }

    /**
     * 获取缓存的登录用户信息
     */
    public FastUsers getUsersInfoCache(String key){
        // 获取缓存对象
        Cache cache = getUsersCache();
        // 从缓存中获取
        FastUsers fastUsers = cache.get(key, FastUsers.class);
        return fastUsers;
    }

    /**
     * 移除缓存登录用户信息
     */
    public void removeUsersCache(String key){
        // 获取缓存对象
        Cache cache = getUsersCache();
        // 把key从缓存中删除
        cache.evict(key);
    }
}
