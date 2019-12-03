package com.example.doorsensor.dao;

import com.example.doorsensor.domain.GateWayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GateWayInfoDao extends JpaRepository<GateWayInfo, Long>,
        JpaSpecificationExecutor<GateWayInfo>{
}
