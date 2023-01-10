package com.example.trs.controller;

import com.example.trs.dto.CompanyDTO;
import com.example.trs.dto.ProjectDTO;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.exceptions.InThePastException;
import com.example.trs.exceptions.ProjectAlreadyExistsException;
import com.example.trs.exceptions.WrongTimeException;
import com.example.trs.mapper.CompanyMapper;
import com.example.trs.mapper.ProjectMapper;
import com.example.trs.model.Company;
import com.example.trs.model.Project;
import com.example.trs.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "project")
@CrossOrigin(origins="http://localhost:4200")
public class ProjectController {
    @Autowired
    ProjectService projectService;


    @GetMapping("ongoing")
    List<ProjectDTO> getAllOnGoing()  {

        List<Project> list= projectService.ongoingProjects();
        return list.stream().map(ProjectMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("")
    List<ProjectDTO> getAll()  {
            List<Project> list= projectService.getAllProjects();

            return list.stream().map(ProjectMapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping("add")
    void addProject(@RequestBody ProjectDTO dto) throws CompanyNotFoundException, ProjectAlreadyExistsException, WrongTimeException, InThePastException {
        Company company = projectService.getCompanyByName(dto.getClientName());
        projectService.addProject(ProjectMapper.toProject(dto, company));
    }

}
