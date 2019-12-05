package com.example.doorsensor.service;

import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.domain.SingleRequest;
import com.example.doorsensor.repository.DoorSensorRepository;
import com.example.doorsensor.repository.SingleRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    @Autowired
    private SingleRequestRepository singleRequestRepository;

    @Autowired
    private DoorSensorRepository doorSensorRepository;

    public SingleRequest save(SingleRequest singleRequest){
        return singleRequestRepository.save(singleRequest);
    }

    public DoorSensor save(DoorSensor doorSensor){
        return doorSensorRepository.save(doorSensor);
    }
}
