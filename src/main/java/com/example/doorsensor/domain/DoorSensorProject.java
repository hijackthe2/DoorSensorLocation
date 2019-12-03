package com.example.doorsensor.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yyl
 * 项目
 */
@Entity
@Table(name = "door_sensor_project", schema = "doorsensor")
public class DoorSensorProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "name", unique = true)
    private String name;

    @Basic
    @Column(name = "gw_eui1")
    private String gwEui1;

    @Basic
    @Column(name = "gw_eui2")
    private String gwEui2;

    @Basic
    @Column(name = "gw_eui3")
    private String gwEui3;

    @Basic
    @Column(name = "gw_eui4")
    private String gwEui4;

    @Basic
    @Column(name = "gw_eui5")
    private String gwEui5;

    @Basic
    @Column(name = "creator")
    private String creator;

    @Basic
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Basic
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "door_sensor_project_id")
    private List<DoorSensor> doorSensors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGwEui1() {
        return gwEui1;
    }

    public void setGwEui1(String gwEui1) {
        this.gwEui1 = gwEui1;
    }

    public String getGwEui2() {
        return gwEui2;
    }

    public void setGwEui2(String gwEui2) {
        this.gwEui2 = gwEui2;
    }

    public String getGwEui3() {
        return gwEui3;
    }

    public void setGwEui3(String gwEui3) {
        this.gwEui3 = gwEui3;
    }

    public String getGwEui4() {
        return gwEui4;
    }

    public void setGwEui4(String gwEui4) {
        this.gwEui4 = gwEui4;
    }

    public String getGwEui5() {
        return gwEui5;
    }

    public void setGwEui5(String gwEui5) {
        this.gwEui5 = gwEui5;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<DoorSensor> getDoorSensors() {
        return doorSensors;
    }

    public void setDoorSensors(List<DoorSensor> doorSensors) {
        this.doorSensors = doorSensors;
    }
}
