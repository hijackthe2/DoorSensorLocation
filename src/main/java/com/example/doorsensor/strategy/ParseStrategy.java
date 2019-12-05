package com.example.doorsensor.strategy;

import com.example.doorsensor.domain.SingleRequest;

public interface ParseStrategy {

    /**
     * @param singleRequest 解析对象
     * @return 解析结果
     */
    boolean parseSingleRequest(SingleRequest singleRequest);
}
