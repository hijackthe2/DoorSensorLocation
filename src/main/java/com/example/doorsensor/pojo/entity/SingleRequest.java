package com.example.doorsensor.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yyl
 * 向LinkWare主动请求的单条接口请求
 */
@Entity
@Table(name = "single_request", schema = "doorsensor")
public class SingleRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 设备唯一编号
     * 节点模块的DevEui
     */
    @Basic
    @Column(name = "dev_eui", length = 32)
    private String devEui;

    /**
     * 应用服务电子识别码
     */
    @Basic
    @Column(name = "app_eui", length = 32)
    private String appEui;

    /**
     * 数据上传时间
     * 格式：yyyMMddHHmmss
     */
    @Basic
    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    /**
     * 设备传输数据
     * 16进制字符串
     */
    @Basic
    @Column(name = "data", length = 32)
    private String data;

    /**
     * 解析结果信息
     * json格式对象
     * 如：{"code": , "message": }
     * 实际情况暂时传null值
     */
    @Basic
    @Column(name = "reserver", length = 32)
    private String reserver;

    /**
     * 数据类型
     * 223表示LoRa节点自发的心跳包，其他表示数据
     */
    @Basic
    @Column(name = "data_type")
    private Integer dataType;

    @Transient
    private List<GateWayInfo> gateWayInfoList;

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

    public String getAppEui() {
        return appEui;
    }

    public void setAppEui(String appEui) {
        this.appEui = appEui;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getReserver() {
        return reserver;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<GateWayInfo> getGateWayInfoList() {
        return gateWayInfoList;
    }

    public void setGateWayInfoList(List<GateWayInfo> gateWayInfoList) {
        this.gateWayInfoList = gateWayInfoList;
    }

    @Override
    public String toString() {
        return "SingleRequest{" +
                "id=" + id +
                ", devEui='" + devEui + '\'' +
                ", appEui='" + appEui + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", data='" + data + '\'' +
                ", reserver='" + reserver + '\'' +
                ", dataType=" + dataType +
                ", gateWayInfoList=" + gateWayInfoList +
                '}';
    }
}
