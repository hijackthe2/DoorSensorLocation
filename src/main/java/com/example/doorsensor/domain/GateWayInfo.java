package com.example.doorsensor.domain;

import javax.persistence.*;

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
    @Column(name = "fcnt_down", length = 32)
    private String fcntDown;

    /**
     * 上行帧区号
     */
    @Basic
    @Column(name = "fcnt_up", length = 32)
    private String fcntUp;

    /**
     * 网关名称
     */
    @Basic
    @Column(name = "gw_eui", length = 32)
    private String gwEui;

    /**
     * 信号强度
     */
    @Basic
    @Column(name = "rssi")
    private float rssi = 0;

    /**
     * 信噪比
     */
    @Basic
    @Column(name = "lsnr")
    private float lsnr = 0;

    /**
     * 高度
     */
    @Basic
    @Column(name = "alti")
    private float alti = 0;

    /**
     * 经度
     */
    @Basic
    @Column(name = "lng")
    private float lng = 0;

    /**
     * 维度
     */
    @Basic
    @Column(name = "lati")
    private float lati = 0;

    /**
     * 对应的单条接口请求
     */
    @Basic
    @Column(name = "single_request_id")
    private long singleRequestId;

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

    public float getRssi() {
        return rssi;
    }

    public void setRssi(float rssi) {
        this.rssi = rssi;
    }

    public float getLsnr() {
        return lsnr;
    }

    public void setLsnr(float lsnr) {
        this.lsnr = lsnr;
    }

    public float getAlti() {
        return alti;
    }

    public void setAlti(float alti) {
        this.alti = alti;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLati() {
        return lati;
    }

    public void setLati(float lati) {
        this.lati = lati;
    }

    public void setLati(Integer lati) {
        this.lati = lati;
    }

    public long getSingleRequestId() {
        return singleRequestId;
    }

    public void setSingleRequestId(long singleRequestId) {
        this.singleRequestId = singleRequestId;
    }
}
