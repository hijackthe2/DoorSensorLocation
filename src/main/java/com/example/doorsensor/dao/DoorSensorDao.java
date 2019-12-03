package com.example.doorsensor.dao;

import com.example.doorsensor.domain.DoorSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorSensorDao extends JpaRepository<DoorSensor, Long>,
        JpaSpecificationExecutor<DoorSensor> {
}
