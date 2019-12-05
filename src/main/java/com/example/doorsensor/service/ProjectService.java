package com.example.doorsensor.service;

import com.example.doorsensor.domain.Project;

public interface ProjectService {

    String add(Project project);

    String update(Project project);

    String delete(Project project);
}
