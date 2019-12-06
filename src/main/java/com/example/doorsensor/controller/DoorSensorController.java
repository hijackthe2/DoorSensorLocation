package com.example.doorsensor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.domain.GateWayInfo;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.service.DoorSensorService;
import com.example.doorsensor.service.ReceiveService;
import com.example.doorsensor.util.DateUtils;
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

    @Autowired
    private DoorSensorService doorSensorService;

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
        singleRequest.setLastUpdateTime(DateUtils.parse(params.getString("last_update_time")));
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
            gateWayInfoList.add(gateWayInfo);
        }
        singleRequest.setGateWayInfoList(gateWayInfoList);
        return receiveService.receiveSingleRequest(singleRequest);
    }

    @ResponseBody
    @RequestMapping("/add_device")
    public String addDevice(@RequestBody JSONObject params) {
        DoorSensor doorSensor = new DoorSensor();
        doorSensor.setDevEui(params.getString("deveui"));
        doorSensor.setDevName(params.getString("devname"));
        doorSensor.setCarId(params.getLong("carid"));
        doorSensor.setProjectName(params.getString("projectname"));
        return doorSensorService.add(doorSensor);
    }

    @ResponseBody
    @RequestMapping("/add_devices")
    public String addDevices(@RequestBody JSONObject params) {
        List<DoorSensor> doorSensors = new ArrayList<>();
        JSONArray array = params.getJSONArray("devices");
        for (int i = 0; i < params.size(); ++i) {
            JSONObject object = array.getJSONObject(i);
            DoorSensor doorSensor = new DoorSensor();
            doorSensor.setDevEui(object.getString("deveui"));
            doorSensor.setDevName(object.getString("devname"));
            doorSensor.setCarId(object.getLong("carid"));
            doorSensor.setProjectName(object.getString("projectname"));
            doorSensors.add(doorSensor);
        }
        return doorSensorService.addBatch(doorSensors);
    }

    @ResponseBody
    @RequestMapping("/update_device")
    public String updateDevice(@RequestBody JSONObject params) {
        DoorSensor doorSensor = new DoorSensor();
        doorSensor.setDevName(params.getString("devname"));
        doorSensor.setCarId(params.getLong("carid"));
        doorSensor.setProjectName(params.getString("projectname"));
        return doorSensorService.update(doorSensor);
    }

    @ResponseBody
    @RequestMapping("/delete_device")
    public String deleteDevice(@RequestBody JSONObject params) {
        return doorSensorService.delete(params.getString("deveui"));
    }

    @ResponseBody
    @RequestMapping("/list_devices_status")
    public String listDevicesStatus() {
        return doorSensorService.listStatus(0, 10);
    }

    @ResponseBody
    @RequestMapping("/list_devices_alert")
    public String listDevicesAlert(@RequestBody JSONObject params) {
        return doorSensorService.listByBindAndAlert(true, params.getBoolean("is_alert"), 0, 10);
    }

    @ResponseBody
    @RequestMapping("/list_devices")
    public String listDevices() {
        return doorSensorService.listAll(0, 10);
    }

    @ResponseBody
    @RequestMapping("/bind")
    public String bind(@RequestBody JSONObject params) {
        return doorSensorService.updateBind(params.getString("devname"), params.getBoolean("is_bind"));
    }

}
