package com.vike.weixin.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vike.weixin.pojo.VerificationCodeRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author: lsl
 * @createDate: 2019/10/25
 */
public class LocalCache {

    /**
     * 构建缓存容器用于储存短信验证码请求
     * 30分钟后过期
     */
    private static LoadingCache<String, VerificationCodeRequest> verificatonCodeRequestCache = CacheBuilder.newBuilder()
            .initialCapacity(500)
            .concurrencyLevel(10)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, VerificationCodeRequest>() {
                @Override
                public VerificationCodeRequest load(String s){
                    return null;
                }
            });

    /**储存获取短信验证码请求*/
    public static void putVerificatonCodeRequest(String serialNumber, VerificationCodeRequest verificationCodeRequest){
        verificatonCodeRequestCache.put(serialNumber,verificationCodeRequest);
    }

    /**通过serialNumber获取短信验证码请求*/
    public static VerificationCodeRequest getVerificatonCodeRequest(String serialNumber){
        return verificatonCodeRequestCache.getIfPresent(serialNumber);
    }
}
