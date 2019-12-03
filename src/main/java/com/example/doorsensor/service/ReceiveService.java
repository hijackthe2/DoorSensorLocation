package com.example.doorsensor.service;

import com.example.doorsensor.domain.SingleRequest;

/**
 * @author yyl
 * 解析来自LinkWare的数据的服务接口
 */
public interface ReceiveService {

    boolean receiveSingleRequest(SingleRequest singleRequest);
}
