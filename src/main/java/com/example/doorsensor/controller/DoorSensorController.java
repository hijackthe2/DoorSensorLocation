package com.example.doorsensor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.GateWayInfo;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.service.ReceiveService;
import com.example.doorsensor.util.DateUtil;
import com.example.doorsensor.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1")
public class DoorSensorController {

    @Autowired
    private ReceiveService receiveService;

    /**
     * 解析从LinkWare平台请求得到的单条接口请求
     * @param params 请求得到的json对象
     */
    @ResponseBody
    @RequestMapping("/linkware")
    public String receiveSingleRequest(@RequestBody JSONObject params){
        SingleRequest singleRequest = new SingleRequest();
        singleRequest.setDevEui(params.getString("mac"));
        singleRequest.setAppEui(params.getString("appeui"));
        singleRequest.setLastUpdateTime(DateUtil.parse(params.getString("last_update_time")));
        singleRequest.setData(params.getString("data"));
        singleRequest.setReserver(params.getString("reserver"));
        singleRequest.setDataType(params.getInteger("data_type"));
        List<GateWayInfo> gateWayInfoList = new ArrayList<>();
        JSONArray array = params.getJSONArray("gateways");
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            GateWayInfo gateWayInfo = new GateWayInfo();
            gateWayInfo.setFcntDown(object.getString("fcntdown"));
            gateWayInfo.setFcntUp(object.getString("fcntup"));
            gateWayInfo.setGwEui(object.getString("gweui"));
            gateWayInfo.setRssi(object.getInteger("rssi"));
            gateWayInfo.setLsnr(object.getInteger("lsnr"));
            gateWayInfo.setAlti(object.getInteger("alti"));
            gateWayInfo.setLng(object.getInteger("lng"));
            gateWayInfo.setLati(object.getInteger("lati"));
            gateWayInfo.setSingleRequest(singleRequest);
            gateWayInfoList.add(gateWayInfo);
        }
        singleRequest.setGateWayInfoList(gateWayInfoList);
        return receiveService.receiveSingleRequest(singleRequest) ?
                ResultUtil.success("success") : ResultUtil.fail(-1, "receive single request failed");
    }
}
