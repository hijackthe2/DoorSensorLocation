package com.example.doorsensor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.repository.DoorSensorRepository;
import com.example.doorsensor.repository.ProjectRepository;
import com.example.doorsensor.service.DoorSensorService;
import com.example.doorsensor.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public String add(DoorSensor doorSensor) {
        if (projectRepository.findOneByName(doorSensor.getProjectName()) == null) {
            log.warn("添加 -- 设备 DevEui {} 绑定的项目 {} 不存在",
                    doorSensor.getDevEui(), doorSensor.getProjectName());
            return ResponseUtils.fail("project not found");
        }
        if (doorSensorRepository.findOneByDevEui(doorSensor.getDevEui()) != null) {
            log.warn("添加 -- 设备 DevEui {} 已存在", doorSensor.getDevEui());
            return ResponseUtils.fail("already existed");
        }
        doorSensor.setCreateTime(LocalDateTime.now());
        boolean bind = doorSensor.getCarId() != null && doorSensor.getCarId() > 0;
        doorSensor.setBind(bind);
        doorSensor.setAlert(false);
        doorSensor.setSensorStatus(0);
        doorSensor.setOpen(false);
        doorSensor.setBatteryStatus(0);
        doorSensor.setRemoved(false);
        doorSensor.setKeyStatus(0);
        doorSensor.setIndex(-1);
        doorSensor.setUpdateTime(LocalDateTime.now());
        doorSensorRepository.save(doorSensor);
        log.info("添加 -- 设备 DevEui {} 添加成功", doorSensor.getDevEui());
        return ResponseUtils.success("add success");
    }

    @Transactional // 默认只能捕获RuntimeException
    @Override
    public String addBatch(List<DoorSensor> doorSensors) {
        for (DoorSensor doorSensor : doorSensors) {
            String response = add(doorSensor);
            try {
                JSONObject object = JSON.parseObject(response);
                if (object.getInteger("code") != 0) {
                    throw new RuntimeException("批量添加 -- 设备批量添加失败");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                log.warn("批量添加 -- 设备批量添加失败");
                return ResponseUtils.fail("add batch fail");
            }
        }
        log.info("批量添加 -- 设备批量添加成功");
        return ResponseUtils.success("add batch success");
    }

    @Transactional
    @Override
    public String update(DoorSensor doorSensor) {
        DoorSensor updateSensor = doorSensorRepository.findOneByDevEui(doorSensor.getDevEui());
        if (updateSensor == null) {
            log.warn("更新 -- 设备 DevEui {} 不存在", doorSensor.getDevEui());
            return ResponseUtils.fail("not found");
        }
        if (projectRepository.findOneByName(doorSensor.getProjectName()) == null) {
            log.warn("更新 -- 设备 DevEui {} 的项目 {} 不存在", doorSensor.getDevEui(), doorSensor.getProjectName());
            return ResponseUtils.fail("not found");
        }
        updateSensor.setDevName(doorSensor.getDevName());
        updateSensor.setCarId(doorSensor.getCarId());
        updateSensor.setProjectName(doorSensor.getProjectName());
        updateSensor.setUpdateTime(LocalDateTime.now());
        doorSensorRepository.save(updateSensor);
        log.info("更新 -- 设备 DevEui {} 更新成功", doorSensor.getDevEui());
        return ResponseUtils.success("update success");
    }

    @Transactional
    @Override
    public String delete(String devEui) {
        DoorSensor deleteSensor = doorSensorRepository.findOneByDevEui(devEui);
        if (deleteSensor == null) {
            log.warn("删除 -- 设备 DevEui {} 不存在", devEui);
            return ResponseUtils.fail("not found");
        }
        doorSensorRepository.deleteOneByDevEui(devEui);
        log.info("删除 -- 设备 DevEui {} 删除成功", devEui);
        return ResponseUtils.success("delete success");
    }

    @Transactional
    @Override
    public String bind(String devEui, Long carId) {
        DoorSensor bindSensor = doorSensorRepository.findOneByDevEui(devEui);
        if (bindSensor == null) {
            log.warn("绑定 -- 设备 DevEui {} 不存在", devEui);
            return ResponseUtils.fail("not found");
        }
        bindSensor.setBind(true);
        bindSensor.setDevEui(devEui);
        bindSensor.setCarId(carId);
        bindSensor.setUpdateTime(LocalDateTime.now());
        doorSensorRepository.save(bindSensor);
        log.info("绑定 -- 设备 DevEui {} 绑定成功", devEui);
        return ResponseUtils.success("bind success");
    }

    /**
     * 解绑，并重置默认值
     */
    @Transactional
    @Override
    public String unbind(String devEui) {
        DoorSensor unbindSensor = doorSensorRepository.findOneByDevEui(devEui);
        if (unbindSensor == null) {
            log.warn("解绑 -- 设备 DevEui {} 不存在", devEui);
            return ResponseUtils.fail("not found");
        }
        unbindSensor.setBind(false);
        unbindSensor.setDevEui(devEui);
        unbindSensor.setCarId(-1L);
        unbindSensor.setIndex(-1);
        unbindSensor.setProjectName("");
        unbindSensor.setAlert(false);
        unbindSensor.setSensorStatus(0);
        unbindSensor.setOpen(false);
        unbindSensor.setBatteryStatus(0);
        unbindSensor.setRemoved(false);
        unbindSensor.setKeyStatus(0);
        unbindSensor.setUpdateTime(LocalDateTime.now());
        doorSensorRepository.save(unbindSensor);
        log.info("解绑 -- 设备 DevEui {} 解绑成功", devEui);
        return ResponseUtils.success("unbind success");
    }

    @Override
    public String listAll(Integer page, Integer size) {
        page = null == page ? 0 : page;
        size = null == size ? 10 : size;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "update_time");
        Page<DoorSensor> doorSensors = doorSensorRepository.findAll(pageable);
        if(doorSensors == null) {
            log.warn("查询 -- 查询设备失败");
            return ResponseUtils.fail("list devices fail");
        }
        for (DoorSensor doorSensor : doorSensors) {
            doorSensor.setId(null);
            doorSensor.setIndex(null);
        }
        log.info("查询 -- 查询设备成功");
        JSONObject object = new JSONObject();
        object.put("total", doorSensors.getSize());
        object.put("rows", doorSensors.getContent());
        return ResponseUtils.success(object);
    }

    @Override
    public String listByBindAndAlert(boolean bind, boolean alert, Integer page, Integer size) {
        page = null == page ? 0 : page;
        size = null == size ? 10 : size;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "update_time");
        Page<DoorSensor> doorSensors = doorSensorRepository.findAllByBindAndAlert(bind, alert, pageable);
        if (doorSensors == null) {
            log.warn("查询报警 -- 查询设备报警失败");
            return ResponseUtils.fail("list devices alert fail");
        }
        log.info("查询报警 -- 查询设备报警成功");
        JSONObject object = new JSONObject();
        object.put("total", doorSensors.getSize());
        object.put("rows", doorSensors.getContent());
        return ResponseUtils.success(object);
    }

    @Override
    public String listByBind(boolean bind, Integer page, Integer size) {
        page = null == page ? 0 : page;
        size = null == size ? 10 : size;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "update_time");
        Page<DoorSensor> doorSensors = doorSensorRepository.findAllByBind(false, pageable);
        if (doorSensors == null) {
            log.warn("查询绑定 -- 查询设备绑定失败");
            return ResponseUtils.fail("list device bind fail");
        }
        log.info("查询绑定 -- 查询设备绑定成功");
        JSONObject object = new JSONObject();
        object.put("total", doorSensors.getSize());
        object.put("rows", doorSensors.getContent());
        return ResponseUtils.success(object);
    }

    @Override
    public String listStatus(Integer page, Integer size) {
        JSONObject alertObject = JSON.parseObject(listByBindAndAlert(true, true, null, null));
        if (alertObject.getInteger("code") != 0) {
            log.warn("绑定报警车辆查询 -- 报警车辆查询失败");
            return ResponseUtils.fail("list devices alert fail");
        }
        JSONObject normalObject = JSON.parseObject(listByBindAndAlert(true, false, null, null));
        if (normalObject.getInteger("code") != 0) {
            log.warn("绑定报警车辆查询 -- 正常车辆查询失败");
            return ResponseUtils.fail("list devices normal fail");
        }
        JSONObject unBindObject = JSON.parseObject(listByBind(false, null, null));
        if (unBindObject.getInteger("code") != 0) {
            log.warn("绑定报警车辆查询 -- 解绑车辆查询失败");
            return ResponseUtils.fail("list devices unbind fail");
        }
        log.info("绑定报警车辆查询 -- 绑定报警车辆查询成功");
        JSONObject object = new JSONObject();
        object.put("alert", alertObject.get("msg"));
        object.put("normal", normalObject.get("msg"));
        object.put("unbind", unBindObject.get("msg"));
        return ResponseUtils.success(object);
    }

}
