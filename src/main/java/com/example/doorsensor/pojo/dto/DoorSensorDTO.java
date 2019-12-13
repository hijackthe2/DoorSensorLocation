package com.example.doorsensor.pojo.dto;

import java.time.LocalDateTime;

/**
 * @author yyl
 * 适用于DoorSensor类的DTO
 */
public class DoorSensorDTO {

    private String devEui;
    private String devName;
    private String carId;
    private Boolean bind;
    private Boolean alert;
    private Boolean open;
    private Boolean removed;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String projectName;
    private Integer sensorStatus;
    private Integer batteryStatus;
    private Integer keyStatus;

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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Boolean getBind() {
        return bind;
    }

    public void setBind(Boolean bind) {
        this.bind = bind;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
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

    public Integer getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(Integer batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(Integer keyStatus) {
        this.keyStatus = keyStatus;
    }

    @Override
    public String toString() {
        return "DoorSensorDTO{" +
                "devEui='" + devEui + '\'' +
                ", devName='" + devName + '\'' +
                ", carId='" + carId + '\'' +
                ", bind=" + bind +
                ", alert=" + alert +
                ", open=" + open +
                ", removed=" + removed +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", projectName='" + projectName + '\'' +
                ", sensorStatus=" + sensorStatus +
                ", batteryStatus=" + batteryStatus +
                ", keyStatus=" + keyStatus +
                '}';
    }
}
