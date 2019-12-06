package com.example.doorsensor.service;

import com.example.doorsensor.domain.DoorSensor;

import java.util.List;

public interface DoorSensorService {

    String add(DoorSensor doorSensor);

    String addBatch(List<DoorSensor> doorSensors);

    String update(DoorSensor doorSensor);

    String delete(String devEui);

    String listByBindAndAlert(boolean bind, boolean alert, Integer page, Integer size);

    String listAll(Integer page, Integer size);

    String updateBind(String devEui, boolean bind);

}
