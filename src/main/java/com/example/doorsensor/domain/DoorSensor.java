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

    /**
     * 匹配门磁解析规则，该属性不加入数据库的字段中
     */
    @Transient
    public static String CLASSNAME = "DoorSensor";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *设备的devEui
     */
    @Basic
    @Column(name = "dev_eui", length = 32)
    private String devEui;

    @Basic
    @Column(name = "dev_name", length = 32)
    private String devName;

    /**
     * 车架号
     */
    @Basic
    @Column(name = "car_id")
    private Long carId = null;

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
    @Basic
    @Column(name = "project_pk_name", length = 32)
    private String projectName;

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
    private Boolean isOpen = false;

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
    private Boolean isRemoved = false;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(Integer sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public Boolean isOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Integer getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(Integer batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public Boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
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
