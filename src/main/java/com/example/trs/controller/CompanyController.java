package com.example.trs.controller;

import com.example.trs.dto.CompanyDTO;
import com.example.trs.mapper.CompanyMapper;
import com.example.trs.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "company")
@CrossOrigin(origins="http://localhost:4200")
public class CompanyController {
    @Autowired
    ProjectService projectService;



    @GetMapping("")
    List<CompanyDTO>   getAll()  {

        return projectService.getAllCompanies().stream().map(x-> CompanyMapper.toDTO(x)).collect(Collectors.toList());
    }






}
