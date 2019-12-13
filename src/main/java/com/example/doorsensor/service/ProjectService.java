package com.example.doorsensor.service;

import com.alibaba.fastjson.JSONObject;
import com.example.doorsensor.domain.Project;

public interface ProjectService {

    JSONObject add(Project project);

    JSONObject update(Project project);

    JSONObject delete(Project project);
}
