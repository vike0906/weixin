package com.vike.weixin.service.serviceImpl;

import com.vike.weixin.dao.FansInfoRepository;
import com.vike.weixin.dao.FansRepository;
import com.vike.weixin.entity.Fans;
import com.vike.weixin.entity.FansInfo;
import com.vike.weixin.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: lsl
 * @createDate: 2019/10/23
 */
@Service
public class FansServiceImpl implements FansService {

    @Autowired
    FansRepository fansRepository;
    @Autowired
    FansInfoRepository fansInfoRepository;

    @Override
    public Fans saveFans(Fans fans) {
        return fansRepository.save(fans);
    }

    @Override
    public Optional<Fans> findByOpenId(String openId) {
        return fansRepository.findFansByOpenId(openId);
    }

    @Override
    public FansInfo saveFansInfo(FansInfo fansInfo) {
        return fansInfoRepository.save(fansInfo);
    }

    @Override
    public Optional<FansInfo> findByFinsId(long fansId) {
        return fansInfoRepository.findFansInfoByFansId(fansId);
    }
}
