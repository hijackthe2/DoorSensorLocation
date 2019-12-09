package com.example.doorsensor.service;

public interface WxSignatureService {

    String getAccessToken(String appId, String secret);

    String getJsApiTicket(String accessToken);

    String getSignature(String url, String appId, String secret);
}
