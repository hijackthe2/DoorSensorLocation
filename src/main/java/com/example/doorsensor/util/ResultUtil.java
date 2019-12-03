package com.example.doorsensor.util;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {

    public static String success(Object info){
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", info);
        return object.toJSONString();
    }

    public static String fail(Integer code, Object info){
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", info);
        return object.toJSONString();
    }
}
