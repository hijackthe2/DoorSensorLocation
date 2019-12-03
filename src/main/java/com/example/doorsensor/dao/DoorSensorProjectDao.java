package com.example.doorsensor.dao;

import com.example.doorsensor.domain.DoorSensorProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorSensorProjectDao extends JpaRepository<DoorSensorProject, Long>,
        JpaSpecificationExecutor<DoorSensorProject> {
}
