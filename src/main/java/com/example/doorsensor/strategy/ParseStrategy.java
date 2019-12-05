package com.example.doorsensor.strategy;

import com.example.doorsensor.domain.SingleRequest;

import java.util.Map;

public interface ParseStrategy {

    /**
     * 使用类名进行强制类型转换
     * 使用Map进行层与层之间的数据传输
     * @param singleRequest 解析对象
     * @return 解析结果
     */
    Map<String, Object> parseSingleRequest(SingleRequest singleRequest);
}
