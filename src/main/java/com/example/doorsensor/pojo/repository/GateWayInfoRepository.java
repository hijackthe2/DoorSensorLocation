package com.example.doorsensor.pojo.repository;

import com.example.doorsensor.pojo.entity.GateWayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GateWayInfoRepository extends JpaRepository<GateWayInfo, Long>,
        JpaSpecificationExecutor<GateWayInfo>{
}
