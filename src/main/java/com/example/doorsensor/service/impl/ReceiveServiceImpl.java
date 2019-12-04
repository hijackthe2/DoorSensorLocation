package com.example.doorsensor.service.impl;

import com.example.doorsensor.dao.SingleRequestDao;
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
    private SingleRequestDao singleRequestDao;

    @Transactional
    @Override
    public boolean receiveSingleRequest(SingleRequest singleRequest) {
        if(singleRequestDao.save(singleRequest) == null){
            log.warn("单条接口请求的数保存失败");
            return false;
        }
        //TODO 注册模式
        return true;
    }
}
