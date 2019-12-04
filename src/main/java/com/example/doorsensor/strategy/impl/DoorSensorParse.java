package com.example.doorsensor.strategy.impl;

import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.domain.GateWayInfo;
import com.example.doorsensor.domain.Project;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.repository.DoorSensorRepository;
import com.example.doorsensor.repository.ProjectRepository;
import com.example.doorsensor.strategy.ParseStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component(value = "DoorSensor")
@Slf4j
public class DoorSensorParse implements ParseStrategy {

    @Autowired
    private DoorSensorRepository doorSensorRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Object parse(Object object) {
        SingleRequest singleRequest = (SingleRequest) object;
        DoorSensor doorSensor = doorSensorRepository.findOneByDevEui(singleRequest.getDevEui());
        if(doorSensor == null){
            log.warn("设备 DevEui " + singleRequest.getDevEui() + " 不存在");
            return null;
        }
        String data = singleRequest.getData();
//        String type = data.substring(0, 2); //为"03"，表示类型是门磁
        doorSensor.setOpen(Integer.parseInt(data.substring(2, 4), 16) == 3);
        int status = Integer.parseInt(data.substring(4, 6), 16);
        doorSensor.setSensorStatus((status >> 6) & 0x03);
        doorSensor.setBatteryStatus((status >> 4) & 0x03);
        doorSensor.setRemoved(((status >> 3) & 0x01) == 0);
        doorSensor.setKeyStatus(status & 0x07);
        doorSensor.setUpdateTime(LocalDateTime.now());
        int index = Integer.parseInt(data.substring(6, 8), 16);
        //TODO 计数处理 报警处理
        boolean alerting = !hasExactGateWays(singleRequest, doorSensor) || !hasLegalIndex(doorSensor, index) || doorSensor.getAlert();
        doorSensor.setAlert(alerting);
        doorSensor.setIndex(index);
        if(alerting){
            log.warn("设备 DevEui " + doorSensor.getDevEui() + " 报警中");
        }
        return doorSensor;
    }

    /**
     * 网关信息是否对应不上
     */
    private boolean hasExactGateWays(SingleRequest singleRequest, DoorSensor doorSensor){
        Project project = projectRepository.findOneById(doorSensor.getProject().getId());
        if(project == null){
            log.warn("设备 DevEui " + doorSensor.getDevEui() + " 不存在与任何一个项目中");
            return false;
        }
        List<String> gwEuiList = project.getAllGwEui();
        List<GateWayInfo> gateWayInfoList = singleRequest.getGateWayInfoList();
        if(gwEuiList.size() != gateWayInfoList.size()){
            log.warn("设备 DevEui " + doorSensor.getDevEui() + " 绑定的项目网关数量与接收到该门磁信息的网关数量不相同");
            return false;
        }
        for(GateWayInfo gateWayInfo : gateWayInfoList){
            if(!gwEuiList.contains(gateWayInfo.getGwEui())){
                log.warn("设备 DevEui " + doorSensor.getDevEui() + " 绑定的项目不存在网关 GwEui " + gateWayInfo.getGwEui());
                return false;
            }
        }
        return true;
    }

    /**
     * 包的计数是否连续
     */
    private boolean hasLegalIndex(DoorSensor doorSensor, int dataIndex){
        return (doorSensor.getIndex() + 1) % 255 == dataIndex;
    }
}
