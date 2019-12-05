package com.example.doorsensor.service.impl;

import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.factory.ParseStrategyBeanFactory;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.service.ReceiveService;
import com.example.doorsensor.service.SaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private ParseStrategyBeanFactory parseStrategyBeanFactory;

    @Autowired
    private SaveService saveService;

    @Transactional
    @Override
    public boolean receiveSingleRequest(SingleRequest singleRequest) {
        if(saveService.save(singleRequest) == null){
            log.warn("单条接口请求的数保存失败");
            return false;
        }
        //TODO 向函数中传递能够从singleRequest中获取的、指向唯一解析类的id
        Map<String, Object> map = parseStrategyBeanFactory.
                getParseStrategyResult(DoorSensor.CLASSNAME).parseSingleRequest(singleRequest);
        if(map == null){
            log.warn("设备数据解析失败");
            return false;
        }

        //TODO 向函数中传递能够从singleRequest中获取的、指向唯一解析类的Class，或者在解析规则中直接保存解析后的对象
        saveService.save((DoorSensor) map.get("data"));
        log.info("设备 DevEui " + singleRequest.getDevEui() + "解析/保存数据 " + singleRequest.getData() + " 成功");
        return true;
    }
}
