package com.example.doorsensor.service.impl;

import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.factory.ParseStrategyBeanFactory;
import com.example.doorsensor.repository.DoorSensorRepository;
import com.example.doorsensor.repository.SingleRequestRepository;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.service.ReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private SingleRequestRepository singleRequestRepository;

    @Autowired
    private ParseStrategyBeanFactory parseStrategyBeanFactory;

    @Autowired
    private DoorSensorRepository doorSensorRepository;

    @Transactional
    @Override
    public boolean receiveSingleRequest(SingleRequest singleRequest) {
        if(singleRequestRepository.save(singleRequest) == null){
            log.warn("单条接口请求的数保存失败");
            return false;
        }
        //TODO 应向函数中传递一个父类对象的唯一id
        DoorSensor doorSensor = (DoorSensor) parseStrategyBeanFactory.
                getParseStrategyResult(DoorSensor.CLASSNAME).parse(singleRequest);
        if(doorSensor == null){
            log.warn("设备数据解析失败");
            return false;
        }
        doorSensorRepository.save(doorSensor);
        return true;
    }
}
