package com.example.doorsensor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.service.WxTicketService;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class WxTicketServiceImpl implements WxTicketService {

    private static String ACCESS_TOKEN = "";
    private static long TOKEN_EXPIRES_IN= 7200L;
    private static long TOKEN_TIMESTAMP= 0L;

    private static String JS_API_TICKET = "";
    private static long TICKET_EXPIRES_IN= 7200L;
    private static long TICKET_TIMESTAMP = 0L;

    @Override
    public String getAccessToken(String appId, String secret) {
        long now = System.currentTimeMillis() / 1000;
        if (now - TOKEN_EXPIRES_IN < TOKEN_TIMESTAMP) {
            return ACCESS_TOKEN;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
                + "appid=" + appId + "&secret=" + secret;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String responseBody = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseBody = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseBody == null) {
            return null;
        }
        JSONObject object = JSON.parseObject(responseBody);
        switch (object.getInteger("errcode")) {
            case -1:
                try {
                    Thread.sleep(2000);
                    return getAccessToken(appId, secret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            case 0:
                TOKEN_EXPIRES_IN = object.getLongValue("expires_n");
                TOKEN_TIMESTAMP = System.currentTimeMillis() / 1000;
                ACCESS_TOKEN = object.getString("access_token");
                return ACCESS_TOKEN;
            case 40001:
                object.put("specification", "AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性");
                break;
            case 40002:
                object.put("specification", "请确保grant_type字段值为client_credential");
                break;
            case 40164:
                object.put("specification", "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）");
                break;
            default:
                object.put("specification", "none");
        }
        return null;
    }

    @Override
    public String getJsApiTicket(String accessToken) {
        long now = System.currentTimeMillis() / 1000;
        if (now - TICKET_EXPIRES_IN < TICKET_TIMESTAMP) {
            return JS_API_TICKET;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                + accessToken + "&type=jsapi";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String responseBody = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseBody = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseBody == null) {
            return null;
        }
        JSONObject object = JSON.parseObject(responseBody);
        if (object.getInteger("errcode") != 0) {
            return null;
        }
        TICKET_TIMESTAMP = System.currentTimeMillis() / 1000;
        TICKET_EXPIRES_IN = object.getLongValue("expires_in");
        JS_API_TICKET = object.getString("ticket");
        return JS_API_TICKET;
    }

    @Override
    public String getSignature() {
        return null;
    }
}
