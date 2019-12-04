package com.example.doorsensor.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author yyl
 * 门磁信息与状态
 */
@Entity
@Table(name = "door_sensor", schema = "doorsensor")
public class DoorSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     *设备的devEui
     */
    @Basic
    @Column(name = "dev_eui")
    private String devEui;

    @Basic
    @Column(name = "dev_name")
    private String devName;

    /**
     * 车架号
     */
    @Basic
    @Column(name = "car_id")
    private long carId = -1;

    /**
     * 是否绑定车辆
     */
    @Basic
    @Column(name = "is_bind")
    private Boolean isBind = false;

    /**
     * 设备异常报警
     */
    @Basic
    @Column(name = "is_alert")
    private Boolean isAlert = false;

    /**
     * 设备进入数据库的时间
     * 格式：yyyMMddHHmmss
     */
    @Basic
    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 设备更新数据的时间
     * 格式：yyyMMddHHmmss
     */
    @Basic
    @Column(name = "update_time")
    private LocalDateTime updateTime = LocalDateTime.now();

    /**
     * 设备接入项目的id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "door_sensor_project_id")
    private DoorSensorProject project;

    /**
     * 传感器状态 0正常 1故障 2报警 其他（保留）
     */
    @Basic
    @Column(name = "sensor_status")
    private Integer sensorStatus = 0;

    /**
     * 门磁状态 true(3)开门 false(0)关门
     */
    @Basic
    @Column(name = "is_open")
    private boolean isOpen = false;

    /**
     * 电池状态 0正常 1低电 其他（保留）
     */
    @Basic
    @Column(name = "battery_status")
    private Integer batteryStatus = 0;

    /**
     * 防拆状态 false(0)正常 true(1)被拆
     */
    @Basic
    @Column(name = "is_removed")
    private boolean isRemoved = false;

    /**
     * 按键状态 0正常 1测试 2消音
     */
    @Basic
    @Column(name = "key_status")
    private Integer keyStatus = 0;

    /**
     * 计数 0~255 循环计数
     */
    @Basic
    @Column(name = "data_index")
    private Integer index = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDevEui() {
        return devEui;
    }

    public void setDevEui(String devEui) {
        this.devEui = devEui;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Boolean getBind() {
        return isBind;
    }

    public void setBind(Boolean bind) {
        isBind = bind;
    }

    public Boolean getAlert() {
        return isAlert;
    }

    public void setAlert(Boolean alert) {
        isAlert = alert;
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

    public DoorSensorProject getProject() {
        return project;
    }

    public void setProject(DoorSensorProject project) {
        this.project = project;
    }

    public Integer getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(Integer sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Integer getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(Integer batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(Integer keyStatus) {
        this.keyStatus = keyStatus;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
