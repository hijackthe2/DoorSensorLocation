package com.example.doorsensor.pojo.repository;

import com.example.doorsensor.pojo.entity.DoorSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying //更新数据库时（删除、更新），需要加上此注解
    void deleteOneByDevEui(String devEui);

    @Query(value = "select * from door_sensor", nativeQuery = true)
    Page<DoorSensor> findAll(Pageable pageable);
}
