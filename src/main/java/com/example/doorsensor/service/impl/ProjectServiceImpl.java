package com.example.doorsensor.service.impl;

import com.example.doorsensor.domain.DoorSensor;
import com.example.doorsensor.domain.Project;
import com.example.doorsensor.repository.DoorSensorRepository;
import com.example.doorsensor.repository.ProjectRepository;
import com.example.doorsensor.service.ProjectService;
import com.example.doorsensor.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DoorSensorRepository doorSensorRepository;

    @Transactional
    @Override
    public String add(Project project) {
        if (!isProjectNotExisting(project)) {
            log.warn("添加 -- 项目 {} 已存在", project.getName());
            return ResponseUtils.fail("add fail");
        }
        project.setUpdateTime(LocalDateTime.now());
        if (projectRepository.save(project) == null) {
            log.warn("添加 -- 项目 {} 添加失败", project.getName());
            return ResponseUtils.fail("add fail");
        }
        log.info("添加 -- 项目 {} 添加成功", project.getName());
        return ResponseUtils.success("add success");
    }

    @Transactional
    @Override
    public String update(Project project) {
        if (isProjectNotExisting(project)) {
            log.warn("更新 -- 项目 {} 不存在", project.getName());
            return ResponseUtils.fail("not found");
        }
        project.setUpdateTime(LocalDateTime.now());
        if (projectRepository.save(project) == null) {
            log.warn("更新 -- 项目 {} 更新失败", project.getName());
            return ResponseUtils.fail("update fail");
        }
        log.info("更新 -- 项目 {} 更新成功", project.getName());
        return ResponseUtils.success("update success");
    }

    @Transactional
    @Override
    public String delete(Project project) {
        if (isProjectNotExisting(project)) {
            log.warn("删除 -- 项目 {} 不存在", project.getName());
            return ResponseUtils.fail("not found");
        }
        List<DoorSensor> doorSensors = doorSensorRepository.findAllByProjectId(project.getId());
        for (DoorSensor doorSensor : doorSensors) {
            doorSensor.setProjectName("");
        }
        doorSensorRepository.saveAll(doorSensors);
        projectRepository.delete(project);
        log.info("删除 -- 项目 {} 删除成功", project.getName());
        return ResponseUtils.success("delete success");
    }

    private boolean isProjectNotExisting(Project project) {
        return projectRepository.findOneById(project.getId()) == null;
    }
}
