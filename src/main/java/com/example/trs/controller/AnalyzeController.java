package com.example.trs.controller;

import com.example.trs.dto.AnalyzeForm;
import com.example.trs.dto.DateDTO;
import com.example.trs.dto.ProjectDTO;
import com.example.trs.mapper.ProjectMapper;
import com.example.trs.model.Project;
import com.example.trs.service.AnalyzeService;
import com.example.trs.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

;

@RestController
@RequestMapping(value = "analyze")
@CrossOrigin(origins="http://localhost:4200")
public class AnalyzeController {
    @Autowired
    AnalyzeService analyzeService;


    @PostMapping("")
    //todo
    void getAllOnGoing(@RequestBody AnalyzeForm dto)  {


    }








}
