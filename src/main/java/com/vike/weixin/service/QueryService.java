package com.vike.weixin.service;

/**
 * @author: lsl
 * @createDate: 2019/10/24
 */
public interface QueryService {

    /**获取验证码*/
    Integer gainVerificationCode(String name, String idCard, String bankCard, String mobile);


    /**验证码校验*/
    Integer checkVerificationCode(String code);

    /**查询结果*/
    String queryCardData(String name, String idCard, String bankCard, String mobile, String code);

    /**全部查询结果*/
}
