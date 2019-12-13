package com.example.doorsensor.util;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtils {

    public static JSONObject success(Object data){
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "success");
        object.put("data", data);
        return object;
    }

    public static JSONObject fail(Object data){
        JSONObject object = new JSONObject();
        object.put("code", 1);
        object.put("msg", "fail");
        object.put("data", data);
        return object;
    }
}
