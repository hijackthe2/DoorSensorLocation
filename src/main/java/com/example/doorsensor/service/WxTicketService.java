package com.example.doorsensor.service;

public interface WxTicketService {

    String getAccessToken(String appId, String secret);

    String getJsApiTicket(String accessToken);

    String getSignature();
}
