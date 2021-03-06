package com.example.doorsensor.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.pojo.entity.DoorSensor;
import com.example.doorsensor.pojo.entity.GateWayInfo;
import com.example.doorsensor.pojo.entity.Project;
import com.example.doorsensor.pojo.entity.SingleRequest;
import com.example.doorsensor.service.DoorSensorService;
import com.example.doorsensor.service.ProjectService;
import com.example.doorsensor.service.ReceiveService;
import com.example.doorsensor.service.WxSignatureService;
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

    @Autowired
    private ProjectService projectService;

    @Autowired
    private WxSignatureService wxSignatureService;

    /**
     * 解析从LinkWare平台请求得到的单条接口请求
     * @param params 请求得到的json对象
     */
    @ResponseBody
    @RequestMapping("/linkware")
    public JSONObject receiveSingleRequest(@RequestBody JSONObject params){
        SingleRequest singleRequest = new SingleRequest();
        singleRequest.setDevEui(params.getString("mac").toUpperCase());
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
            gateWayInfo.setRssi(object.getFloatValue("rssi"));
            gateWayInfo.setLsnr(object.getFloatValue("lsnr"));
            gateWayInfo.setAlti(object.getFloatValue("alti"));
            gateWayInfo.setLng(object.getFloatValue("lng"));
            gateWayInfo.setLati(object.getFloatValue("lati"));
            gateWayInfoList.add(gateWayInfo);
        }
        singleRequest.setGateWayInfoList(gateWayInfoList);
        return receiveService.receiveSingleRequest(singleRequest);
    }

    @ResponseBody
    @RequestMapping("/add_project")
    public JSONObject addProject(@RequestBody JSONObject params) {
        Project project = new Project();
        project.setName(params.getString("projectname"));
        project.setCreator(params.getString("creator"));
        List<String> gwEuiList = new ArrayList<>();
        JSONArray array = params.getJSONArray("gweuis");
        for (int i = 0; i < array.size(); i++) {
            gwEuiList.add(array.getString(i));
        }
        project.setAllGwEuis(gwEuiList);
        return projectService.add(project);
    }

    @ResponseBody
    @RequestMapping("/add_device")
    public JSONObject addDevice(@RequestBody JSONObject params) {
        DoorSensor doorSensor = new DoorSensor();
        doorSensor.setDevEui(params.getString("deveui").toUpperCase());
        doorSensor.setDevName(params.getString("devname"));
        doorSensor.setCarId(params.getString("carid"));
        doorSensor.setProjectName(params.getString("projectname"));
        return doorSensorService.add(doorSensor);
    }

    /**
     * 批量添加
     */
    @ResponseBody
    @RequestMapping("/add_devices")
    public JSONObject addDevices(@RequestBody JSONObject params) {
        List<DoorSensor> doorSensors = new ArrayList<>();
        JSONArray array = params.getJSONArray("devices");
        for (int i = 0; i < params.size(); ++i) {
            JSONObject object = array.getJSONObject(i);
            DoorSensor doorSensor = new DoorSensor();
            doorSensor.setDevEui(object.getString("deveui").toUpperCase());
            doorSensor.setDevName(object.getString("devname"));
            doorSensor.setCarId(object.getString("carid"));
            doorSensor.setProjectName(object.getString("projectname"));
            doorSensors.add(doorSensor);
        }
        return doorSensorService.addBatch(doorSensors);
    }

    /**
     * 更新设备，只能更行设备的信息，限于名称、车号、项目名称
     */
    @ResponseBody
    @RequestMapping("/update_device")
    public JSONObject updateDevice(@RequestBody JSONObject params) {
        DoorSensor doorSensor = new DoorSensor();
        doorSensor.setDevEui(params.getString("deveui").toUpperCase());
        doorSensor.setDevName(params.getString("devname"));
        doorSensor.setCarId(params.getString("carid"));
        doorSensor.setProjectName(params.getString("projectname"));
        return doorSensorService.update(doorSensor);
    }

    @ResponseBody
    @RequestMapping("/delete_device")
    public JSONObject deleteDevice(@RequestBody JSONObject params) {
        return doorSensorService.delete(params.getString("deveui").toUpperCase());
    }

    @ResponseBody
    @RequestMapping("/bind_device")
    public JSONObject bindDevice(@RequestBody JSONObject params) {
        return doorSensorService.bind(params.getString("deveui").toUpperCase(), params.getString("carid"));
    }

    @ResponseBody
    @RequestMapping("/unbind_device")
    public JSONObject unbindDevice(@RequestBody JSONObject params) {
        return doorSensorService.unbind(params.getString("deveui").toUpperCase());
    }

    /**
     * 按照项目，查询设备列表，并分状态返回结果（alert，normal，unbind）
     */
    @ResponseBody
    @RequestMapping("/list_devices_status_project")
    public JSONObject listDevicesByStatusAndProject(@RequestBody JSONObject params) {
        return doorSensorService.
                listStatusAndProject(params.getString("projectname"), params.getInteger("page"), params.getInteger("size"));
    }

    /**
     * 按照项目及是否报警查询
     */
    @ResponseBody
    @RequestMapping("/list_devices_alert_project")
    public JSONObject listDevicesByAlertAndProject(@RequestBody JSONObject params) {
        return doorSensorService.listByBindAndAlertAndProject(true,
                params.getBoolean("is_alert"), params.getString("projectname"), params.getInteger("page"), params.getInteger("size"));
    }

    /**
     * 按照项目返回所有设备，不分状态
     */
    @ResponseBody
    @RequestMapping("/list_devices_project")
    public JSONObject listDevicesByProject(@RequestBody JSONObject params) {
        return doorSensorService.listByProject(params.getString("projectname"), params.getInteger("page"), params.getInteger("size"));
    }

    /**
     * 按照项目及是否绑定查询
     */
    @ResponseBody
    @RequestMapping("/list_devices_bind_project")
    public JSONObject listDevicesByBindAndProject(@RequestBody JSONObject params) {
        return doorSensorService.listByBindAndProject(params.getBoolean("is_bind"), params.getString("projectname"),
                params.getInteger("page"), params.getInteger("size"));
    }

    @ResponseBody
    @RequestMapping("/wx_config")
    public JSONObject wxConfig(@RequestBody JSONObject params) {
        return wxSignatureService
                .getSignature(params.getString("url"), params.getString("appid"), params.getString("secret"));
    }

}
