package com.example.doorsensor.service;

import com.example.doorsensor.domain.DoorSensor;

import java.util.List;

public interface DoorSensorService {

    String add(DoorSensor doorSensor);

    String addBatch(List<DoorSensor> doorSensors);

    String update(DoorSensor doorSensor);

    String bind(String devEui, Long carId);

    String unbind(String devEui);

    String delete(String devEui);

    String listByBindAndAlert(boolean bind, boolean alert, Integer page, Integer size);

    String listAll(Integer page, Integer size);

    String listStatus(Integer page, Integer size);

    String listByBind(boolean bind, Integer page, Integer size);

}
