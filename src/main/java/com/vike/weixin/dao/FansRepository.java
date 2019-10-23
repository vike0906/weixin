package com.vike.weixin.dao;

import com.vike.weixin.entity.Fans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FansRepository extends JpaRepository<Fans,Long>,JpaSpecificationExecutor<Fans> {

    Optional<Fans> findFansByOpenId(String openId);
}
