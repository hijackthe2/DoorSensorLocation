package com.example.doorsensor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.service.WxSignatureService;
import com.example.doorsensor.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class WxSignatureServiceImpl implements WxSignatureService {

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
            log.info("微信 -- 获取微信token成功 {}", ACCESS_TOKEN);
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
            log.info("微信 -- token 应答 {}", responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseBody == null) {
            return null;
        }
        JSONObject object = JSON.parseObject(responseBody);
        if (object.getInteger("errcode") == null) {
            TOKEN_EXPIRES_IN = object.getLongValue("expires_in");
            TOKEN_TIMESTAMP = System.currentTimeMillis() / 1000;
            ACCESS_TOKEN = object.getString("access_token");
            log.info("微信 -- 获取微信token成功 {}", ACCESS_TOKEN);
            return ACCESS_TOKEN;
        }
        switch (object.getInteger("errcode")) {
            case -1:
                try {
                    Thread.sleep(2000);
                    return getAccessToken(appId, secret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            case 0:
                TOKEN_EXPIRES_IN = object.getLongValue("expires_in");
                TOKEN_TIMESTAMP = System.currentTimeMillis() / 1000;
                ACCESS_TOKEN = object.getString("access_token");
                log.info("微信 -- 获取微信token成功 {}", ACCESS_TOKEN);
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
            log.info("微信 -- 获取微信ticket成功 {}", JS_API_TICKET);
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
            log.info("微信 -- ticket 应答 {}", responseBody);
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
        log.info("微信 -- 获取微信ticket成功 {}", JS_API_TICKET);
        return JS_API_TICKET;
    }

    @Override
    public JSONObject getSignature(String url, String appId, String secret) {
        String token = getAccessToken(appId, secret);
        if (token == null) {
            log.warn("微信 -- 获取token失败");
            return ResponseUtils.fail("token fail");
        }
        String ticket = getJsApiTicket(token);
        if (ticket == null) {
            log.warn("微信 -- 获取ticket失败");
            return ResponseUtils.fail("ticket fail");
        }
        String nonceStr = getNonceStr();
        String timestamp = getTimestamp();
        String string1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes(StandardCharsets.UTF_8));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject();
        object.put("noncestr", nonceStr);
        object.put("timestamp", timestamp);
        object.put("jsapi_ticket", ticket);
        object.put("url", url);
        object.put("signature", signature);
        log.info("微信 -- 获取微信signature成功 {}", signature);
        return ResponseUtils.success(object);
    }

    private static String getNonceStr() {
        return UUID.randomUUID().toString();
    }

    private static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
