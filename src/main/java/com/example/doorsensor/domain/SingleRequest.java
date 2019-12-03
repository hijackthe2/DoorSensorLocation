package com.example.doorsensor.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    private long id;

    /**
     * 设备唯一编号
     * 节点模块的DevEui
     */
    @Basic
    @Column(name = "dev_eui")
    private String devEui;

    /**
     * 应用服务电子识别码
     */
    @Basic
    @Column(name = "app_eui")
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
    @Column(name = "data")
    private String data;

    /**
     * 解析结果信息
     * json格式对象
     * 如：{"code": , "message": }
     * 实际情况暂时传null值
     */
    @Basic
    @Column(name = "reserver")
    private String reserver;

    /**
     * 数据类型
     * 223表示LoRa节点自发的心跳包，其他表示数据
     */
    @Basic
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 0到多条网关信息
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_way_info_id")
    private List<GateWayInfo> gateWayInfoList;

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

    public List<GateWayInfo> getGateWayInfoList() {
        return gateWayInfoList;
    }

    public void setGateWayInfoList(List<GateWayInfo> gateWayInfoList) {
        this.gateWayInfoList = gateWayInfoList;
    }
}
