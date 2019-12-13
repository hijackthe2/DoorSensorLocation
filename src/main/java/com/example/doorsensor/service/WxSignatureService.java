package com.example.doorsensor.service;

import com.alibaba.fastjson.JSONObject;

public interface WxSignatureService {

    String getAccessToken(String appId, String secret);

    String getJsApiTicket(String accessToken);

    JSONObject getSignature(String url, String appId, String secret);
}
