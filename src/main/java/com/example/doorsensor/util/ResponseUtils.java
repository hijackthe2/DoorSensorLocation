package com.example.doorsensor.util;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtils {

    public static String success(Object data){
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", data);
        return object.toJSONString();
    }

    public static String fail(Object data){
        JSONObject object = new JSONObject();
        object.put("code", 1);
        object.put("msg", data);
        return object.toJSONString();
    }
}
