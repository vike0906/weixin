package com.vike.weixin.service;

import com.vike.weixin.entity.Fans;
import com.vike.weixin.entity.FansInfo;

import java.util.Optional;

/**
 * @author: lsl
 * @createDate: 2019/10/23
 */
public interface FansService {

    Fans saveFans(Fans fans);

    Optional<Fans> findByOpenId(String openId);

    FansInfo saveFansInfo(FansInfo fansInfo);

    Optional<FansInfo> findByFinsId(long fansId);
}
