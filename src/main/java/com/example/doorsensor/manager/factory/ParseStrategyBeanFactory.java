package com.example.doorsensor.manager.factory;

import com.example.doorsensor.manager.strategy.ParseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 根据匹配id，匹配相应的继承ParseStrategy的类
 * 返回该类的bean
 */
@Component
public class ParseStrategyBeanFactory {

    @Autowired
    private Map<String, ParseStrategy> strategyMap;

    public ParseStrategy getParseStrategyResult(String matchId){
        ParseStrategy parseStrategy = strategyMap.get(matchId);
        try{
            if (parseStrategy == null){
                throw new Exception("类型 " + matchId + " 不存在匹配表中");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return parseStrategy;
    }
}
