package com.example.doorsensor.strategy;

public interface ParseStrategy {

    /**
     * TODO 需要解析的形参和返回的解析结果对象需要分别继承相同的类
     *      策略模式才有效
     * @param object 解析对象
     * @return 解析结果
     */
    Object parse(Object object);
}
