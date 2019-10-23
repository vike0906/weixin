package com.vike.weixin.dao;

import com.vike.weixin.entity.FansInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FansInfoRepository extends JpaRepository<FansInfo,Long>,JpaSpecificationExecutor<FansInfo> {

    Optional<FansInfo> findFansInfoByFansId(long fansId);
}
