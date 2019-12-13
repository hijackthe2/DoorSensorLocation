package com.example.doorsensor.manager.strategy;

import com.example.doorsensor.pojo.entity.SingleRequest;

public interface ParseStrategy {

    /**
     * @param singleRequest 解析对象
     * @return 解析结果
     */
    boolean parseSingleRequest(SingleRequest singleRequest);
}
