package com.example.doorsensor.service.impl;

import com.example.doorsensor.dao.SingleRequestDao;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private SingleRequestDao singleRequestDao;

    @Override
    public boolean receiveSingleRequest(SingleRequest singleRequest) {
        singleRequestDao.save(singleRequest);
        //TODO 注册模式
        return false;
    }
}
