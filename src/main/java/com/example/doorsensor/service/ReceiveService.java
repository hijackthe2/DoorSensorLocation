package com.example.doorsensor.service;

import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.SingleRequest;

/**
 * @author yyl
 * 解析来自LinkWare的数据的服务接口
 */
public interface ReceiveService {

    /**
     * 解析单条接口请求的数据
     * @param singleRequest 单条接口请求的数据
     * @return 成功返回true，失败返回false
     */
    JSONObject receiveSingleRequest(SingleRequest singleRequest);
}
