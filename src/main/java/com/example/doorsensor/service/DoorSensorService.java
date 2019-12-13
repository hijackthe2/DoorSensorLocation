package com.example.doorsensor.service;

import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.DoorSensor;

import java.util.List;

public interface DoorSensorService {

    JSONObject add(DoorSensor doorSensor);

    JSONObject addBatch(List<DoorSensor> doorSensors);

    JSONObject update(DoorSensor doorSensor);

    JSONObject bind(String devEui, Long carId);

    JSONObject unbind(String devEui);

    JSONObject delete(String devEui);

    JSONObject listByBindAndAlert(boolean bind, boolean alert, Integer page, Integer size);

    JSONObject listAll(Integer page, Integer size);

    JSONObject listStatus(Integer page, Integer size);

    JSONObject listByBind(boolean bind, Integer page, Integer size);

}
