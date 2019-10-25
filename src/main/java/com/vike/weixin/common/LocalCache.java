package com.vike.weixin.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vike.weixin.pojo.VerificationCodeRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: lsl
 * @createDate: 2019/10/25
 */
@Slf4j
public class LocalCache {

    /**
     * 构建缓存容器用于储存短信验证码请求
     * 30分钟后过期
     */
    private static LoadingCache<String, VerificationCodeRequest> verificationCodeRequestCache = CacheBuilder.newBuilder()
            .initialCapacity(500)
            .concurrencyLevel(10)
            .expireAfterAccess(20, TimeUnit.MINUTES)
            .build(new CacheLoader<String, VerificationCodeRequest>() {
                @Override
                public VerificationCodeRequest load(String s){
                    return null;
                }
            });

    /**储存获取短信验证码请求*/
    public static void putVerificationCodeRequest(String orderNo, VerificationCodeRequest verificationCodeRequest){
        log.info("储存获取短信验证码请求，当前容器储存数量：{}",verificationCodeRequestCache.size());
        verificationCodeRequestCache.put(orderNo,verificationCodeRequest);
    }

    /**通过serialNumber获取短信验证码请求*/
    public static VerificationCodeRequest getVerificatonCodeRequest(String orderNo){
        log.info("读取获取短信验证码请求，当前容器储存数量：{}",verificationCodeRequestCache.size());
        return verificationCodeRequestCache.getIfPresent(orderNo);
    }
}
