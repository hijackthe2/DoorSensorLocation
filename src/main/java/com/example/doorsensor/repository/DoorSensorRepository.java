package com.example.doorsensor.repository;

import com.example.doorsensor.domain.DoorSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorSensorRepository extends JpaRepository<DoorSensor, Long>,
        JpaSpecificationExecutor<DoorSensor> {

    DoorSensor findOneByDevEui(String devEui);
}
