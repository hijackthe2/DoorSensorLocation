package com.example.doorsensor.pojo.entity;

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
    private Long id;

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
    private Float rssi;

    /**
     * 信噪比
     */
    @Basic
    @Column(name = "lsnr")
    private Float lsnr;

    /**
     * 高度
     */
    @Basic
    @Column(name = "alti")
    private Float alti;

    /**
     * 经度
     */
    @Basic
    @Column(name = "lng")
    private Float lng;

    /**
     * 维度
     */
    @Basic
    @Column(name = "lati")
    private Float lati;

    /**
     * 对应的单条接口请求
     */
    @Basic
    @Column(name = "single_request_id")
    private Long singleRequestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Float getRssi() {
        return rssi;
    }

    public void setRssi(Float rssi) {
        this.rssi = rssi;
    }

    public Float getLsnr() {
        return lsnr;
    }

    public void setLsnr(Float lsnr) {
        this.lsnr = lsnr;
    }

    public Float getAlti() {
        return alti;
    }

    public void setAlti(Float alti) {
        this.alti = alti;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLati() {
        return lati;
    }

    public void setLati(Float lati) {
        this.lati = lati;
    }

    public Long getSingleRequestId() {
        return singleRequestId;
    }

    public void setSingleRequestId(Long singleRequestId) {
        this.singleRequestId = singleRequestId;
    }

    @Override
    public String toString() {
        return "GateWayInfo{" +
                "id=" + id +
                ", fcntDown='" + fcntDown + '\'' +
                ", fcntUp='" + fcntUp + '\'' +
                ", gwEui='" + gwEui + '\'' +
                ", rssi=" + rssi +
                ", lsnr=" + lsnr +
                ", alti=" + alti +
                ", lng=" + lng +
                ", lati=" + lati +
                ", singleRequestId=" + singleRequestId +
                '}';
    }
}
