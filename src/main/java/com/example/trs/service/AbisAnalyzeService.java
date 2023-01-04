package com.example.trs.service;

import com.example.trs.repositories.ActivityJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class AbisAnalyzeService implements AnalyzeService {
    @Autowired
    ActivityJpaRepo activityJpaRepo;

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    EmployeeService employeeService;



}
