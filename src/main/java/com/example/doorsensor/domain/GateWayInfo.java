package com.example.doorsensor.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yyl
 * 单挑接口请求中的网关信息
 */
@Entity
@Table(name = "gate_way_info", schema = "doorsensor")
public class GateWayInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * 下行帧区号
     */
    @Basic
    @Column(name = "fcnt_down")
    private String fcntDown;

    /**
     * 上行帧区号
     */
    @Basic
    @Column(name = "fcnt_up")
    private String fcntUp;

    /**
     * 网关名称
     */
    @Basic
    @Column(name = "gw_eui")
    private String gwEui;

    /**
     * 信号强度
     */
    @Basic
    @Column(name = "rssi")
    private Integer rssi = 0;

    /**
     * 信噪比
     */
    @Basic
    @Column(name = "lsnr")
    private Integer lsnr = 0;

    /**
     * 高度
     */
    @Basic
    @Column(name = "alti")
    private Integer alti = 0;

    /**
     * 经度
     */
    @Basic
    @Column(name = "lng")
    private Integer lng = 0;

    /**
     * 维度
     */
    @Basic
    @Column(name = "lati")
    private Integer lati = 0;

    /**
     * 对应的单条接口请求
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_way_info_id")
    private SingleRequest singleRequest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFcntDown() {
        return fcntDown;
    }

    public void setFcntDown(String fcntDown) {
        this.fcntDown = fcntDown;
    }

    public String getFcntUp() {
        return fcntUp;
    }

    public void setFcntUp(String fcntUp) {
        this.fcntUp = fcntUp;
    }

    public String getGwEui() {
        return gwEui;
    }

    public void setGwEui(String gwEui) {
        this.gwEui = gwEui;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public Integer getLsnr() {
        return lsnr;
    }

    public void setLsnr(Integer lsnr) {
        this.lsnr = lsnr;
    }

    public Integer getAlti() {
        return alti;
    }

    public void setAlti(Integer alti) {
        this.alti = alti;
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    public Integer getLati() {
        return lati;
    }

    public void setLati(Integer lati) {
        this.lati = lati;
    }

    public SingleRequest getSingleRequest() {
        return singleRequest;
    }

    public void setSingleRequest(SingleRequest singleRequest) {
        this.singleRequest = singleRequest;
    }
}
