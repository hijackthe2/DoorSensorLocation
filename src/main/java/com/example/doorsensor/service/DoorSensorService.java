package com.example.doorsensor.service;

import com.example.doorsensor.domain.DoorSensor;

import java.util.List;

public interface DoorSensorService {

    String add(DoorSensor doorSensor, String projectName);

    String addBatch(List<DoorSensor> doorSensors, String projectName);

    String update(DoorSensor doorSensor);

    String delete(DoorSensor doorSensor);

}
