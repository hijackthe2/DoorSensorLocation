package com.example.doorsensor.service.impl;

import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.repository.DoorSensorRepository;
import com.example.doorsensor.repository.ProjectRepository;
import com.example.doorsensor.service.DoorSensorService;
import com.example.doorsensor.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DoorSensorServiceImpl implements DoorSensorService {

    @Autowired
    private DoorSensorRepository doorSensorRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    @Override
    public String add(DoorSensor doorSensor, String projectName) {
        if (isProjectNotExisting(projectName)) {
            log.warn("添加 -- 设备 DevEui {} 的项目 {} 不存在", doorSensor.getDevEui(), projectName);
            return ResponseUtils.fail("project not found");
        }
        if (!isDoorSensorNotExisting(doorSensor)) {
            log.warn("添加 -- 设备 DevEui {} 已存在", doorSensor.getDevEui());
            return ResponseUtils.fail("already existed");
        }
        doorSensor.setUpdateTime(LocalDateTime.now());
        if (doorSensorRepository.save(doorSensor) == null) {
            log.warn("添加 -- 设备 DevEui {} 添加失败", doorSensor.getDevEui());
            return ResponseUtils.fail("add fail");
        }
        log.info("添加 -- 设备 DevEui {} 添加成功", doorSensor.getDevEui());
        return ResponseUtils.success("add success");
    }

    @Transactional
    @Override
    public String addBatch(List<DoorSensor> doorSensors, String projectName) {
        if (isProjectNotExisting(projectName)) {
            log.warn("批量添加 -- 项目 {} 不存在", projectName);
            return ResponseUtils.fail("project not found");
        }
        for (DoorSensor doorSensor : doorSensors) {
            if(!isDoorSensorNotExisting(doorSensor)) {
                log.warn("批量添加 -- 设备 DevEui {} 已存在", doorSensor.getDevEui());
                return ResponseUtils.fail("already existed");
            }
            doorSensor.setUpdateTime(LocalDateTime.now());
        }
        List<DoorSensor> saveSensors = doorSensorRepository.saveAll(doorSensors);
        if (saveSensors == null || saveSensors.size() == 0) {
            log.warn("批量添加 -- 设备批量添加失败");
            return ResponseUtils.fail("add batch fail");
        }
        log.info("批量添加 -- 设备批量添加成功");
        return ResponseUtils.success("add batch success");
    }

    @Transactional
    @Override
    public String update(DoorSensor doorSensor) {
        if (isDoorSensorNotExisting(doorSensor)) {
            log.warn("更新 -- 设备 DevEui {} 不存在", doorSensor.getDevEui());
            return ResponseUtils.fail("not found");
        }
        doorSensor.setUpdateTime(LocalDateTime.now());
        if (doorSensorRepository.save(doorSensor) == null) {
            log.warn("更新 -- 设备 DevEui {} 更新失败", doorSensor.getDevEui());
            return ResponseUtils.fail("update fail");
        }
        log.info("更新 -- 设备 DevEui {} 更新成功", doorSensor.getDevEui());
        return ResponseUtils.success("update success");
    }

    @Transactional
    @Override
    public String delete(DoorSensor doorSensor) {
        if (isDoorSensorNotExisting(doorSensor)) {
            log.warn("删除 -- 设备 DevEui {} 不存在", doorSensor.getDevEui());
            return ResponseUtils.fail("not found");
        }
        doorSensorRepository.delete(doorSensor);
        log.info("删除 -- 设备 DevEui {} 删除成功", doorSensor.getDevEui());
        return ResponseUtils.success("delete success");
    }

    public String listByBind(boolean blind) {
        return null;
    }

    public String listByBindAndAlert(boolean bind, boolean alert) {
        return null;
    }

    private boolean isDoorSensorNotExisting(DoorSensor doorSensor) {
        return doorSensorRepository.findOneByDevEui(doorSensor.getDevEui()) == null;
    }

    private boolean isProjectNotExisting(String projectName) {
        return projectRepository.findOneByName(projectName) == null;
    }
}
