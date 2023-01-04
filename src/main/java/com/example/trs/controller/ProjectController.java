package com.example.trs.controller;

import com.example.trs.dto.ProjectDTO;
import com.example.trs.mapper.ActivityMapper;
import com.example.trs.mapper.ProjectMapper;
import com.example.trs.model.Project;
import com.example.trs.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "project")
@CrossOrigin(origins="http://localhost:4200")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("")
    List<ProjectDTO> getAll() {
        List<Project> projectList = projectService.getAllProjects();
        System.out.println(projectList.stream().map(ProjectMapper::toDTO).collect(Collectors.toList()));
        return projectList.stream().map(ProjectMapper::toDTO).collect(Collectors.toList());
    }


}
