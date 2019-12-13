package com.example.doorsensor.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.domain.GateWayInfo;
import com.example.doorsensor.factory.ParseStrategyBeanFactory;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.repository.GateWayInfoRepository;
import com.example.doorsensor.repository.SingleRequestRepository;
import com.example.doorsensor.service.ReceiveService;
import com.example.doorsensor.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private ParseStrategyBeanFactory parseStrategyBeanFactory;

    @Autowired
    private SingleRequestRepository singleRequestRepository;

    @Autowired
    private GateWayInfoRepository gateWayInfoRepository;

    @Transactional
    @Override
    public JSONObject receiveSingleRequest(SingleRequest singleRequest) {
        long singleRequestId = singleRequestRepository.save(singleRequest).getId();
        for (GateWayInfo gateWayInfo : singleRequest.getGateWayInfoList()) {
            gateWayInfo.setSingleRequestId(singleRequestId);
        }
        gateWayInfoRepository.saveAll(singleRequest.getGateWayInfoList());
        //TODO 向函数中传递能够从singleRequest中获取的、指向唯一解析类的id
        boolean isParseSuccessful = parseStrategyBeanFactory.
                getParseStrategyResult(DoorSensor.CLASSNAME).parseSingleRequest(singleRequest);
        if(!isParseSuccessful){
            log.warn("设备 DevEui {} 解析/保存数据 {} 失败", singleRequest.getDevEui(), singleRequest.getData());
            return ResponseUtils.fail("parse fail");
        }
        log.info("设备 DevEui {} 解析/保存数据 {} 成功", singleRequest.getDevEui(), singleRequest.getData());
        return ResponseUtils.success("parse success");
    }
}
