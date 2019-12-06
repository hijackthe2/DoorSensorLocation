package com.example.doorsensor.repository;

import com.example.doorsensor.domain.DoorSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoorSensorRepository extends JpaRepository<DoorSensor, Long>,
        JpaSpecificationExecutor<DoorSensor> {

    @Query(value = "select * from door_sensor ds where ds.dev_eui = ?1", nativeQuery = true)
    DoorSensor findOneByDevEui(String devEui);

    @Query(value = "select * from door_sensor ds where ds.project_id = ?1", nativeQuery = true)
    List<DoorSensor> findAllByProjectId(long projectId);

    @Query(value = "select * from door_sensor ds where ds.is_bind = ?1 and ds.is_alert = ?2", nativeQuery = true)
    Page<DoorSensor> findAllByBindAndAlert(boolean bind, boolean alert, Pageable pageable);

    @Query(value = "select * from door_sensor ds where ds.is_bind = ?1", nativeQuery = true)
    Page<DoorSensor> findAllByBind(boolean bind, Pageable pageable);

    @Query(value = "delete from door_sensor ds where ds.dev_eui = ?1", nativeQuery = true)
    void deleteOnByDevEui(String devEui);
}
