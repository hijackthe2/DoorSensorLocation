package com.example.doorsensor.factory;

import com.example.doorsensor.strategy.ParseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
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
