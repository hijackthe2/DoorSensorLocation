package com.example.doorsensor.pojo.entity;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yyl
 * 项目
 * POJO 专指只有 setter / getter / toString 的简单类
 */
@Entity
@Table(name = "project", schema = "doorsensor")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "pk_name", length = 32, unique = true, nullable = false)
    private String name;

    @Basic
    @Column(name = "gw_eui1", length = 32)
    private String gwEui1;

    @Basic
    @Column(name = "gw_eui2", length = 32)
    private String gwEui2;

    @Basic
    @Column(name = "gw_eui3", length = 32)
    private String gwEui3;

    @Basic
    @Column(name = "gw_eui4", length = 32)
    private String gwEui4;

    @Basic
    @Column(name = "gw_eui5", length = 32)
    private String gwEui5;

    @Basic
    @Column(name = "creator", length = 32)
    private String creator;

    @Basic
    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    @Basic
    @Column(name = "update_time")
    private LocalDateTime updateTime = LocalDateTime.now();

    public List<String> getAllGwEuis(){
        List<String> gwEuiList = new ArrayList<>();
        if(!StringUtils.isEmpty(gwEui1)){
            gwEuiList.add(gwEui1);
        }
        if(!StringUtils.isEmpty(gwEui2)){
            gwEuiList.add(gwEui2);
        }
        if(!StringUtils.isEmpty(gwEui3)){
            gwEuiList.add(gwEui3);
        }
        if(!StringUtils.isEmpty(gwEui4)){
            gwEuiList.add(gwEui4);
        }
        if(!StringUtils.isEmpty(gwEui5)){
            gwEuiList.add(gwEui5);
        }
        return gwEuiList;
    }

    public void setAllGwEuis(List<String> gwEuis) {
        int index = 0;
        int len = gwEuis.size();
        if (index < len) {
            gwEui1 = gwEuis.get(index);
            index++;
        }
        if (index < len) {
            gwEui2 = gwEuis.get(index);
            index++;
        }
        if (index < len) {
            gwEui3 = gwEuis.get(index);
            index++;
        }
        if (index < len) {
            gwEui4 = gwEuis.get(index);
            index++;
        }
        if (index < len) {
            gwEui5 = gwEuis.get(index);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gwEui1='" + gwEui1 + '\'' +
                ", gwEui2='" + gwEui2 + '\'' +
                ", gwEui3='" + gwEui3 + '\'' +
                ", gwEui4='" + gwEui4 + '\'' +
                ", gwEui5='" + gwEui5 + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
