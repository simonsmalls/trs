package com.example.trs.controller;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.dto.AnalyzeForm;
import com.example.trs.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

@RestController
@RequestMapping(value = "analyze")
@CrossOrigin(origins="http://localhost:4200")
public class AnalyzeController {
    @Autowired
    AnalyzeService analyzeService;


    @PostMapping("")

   public  List<AnalyzeDTO> analyzeBy(@RequestBody AnalyzeForm dto)  {
        if(dto.getProject_Id()!=0 && dto.getStart()!=null && dto.getEnd()!=null && dto.getEmployee_Id()!=0 ){
            return analyzeService.findActivitiesByProjectIdAndEmployeeIdAndDates(dto.getProject_Id(),dto.getEmployee_Id(),dto.getStart(),dto.getEnd());

        }
        if(dto.getProject_Id()!=0 && dto.getStart()!=null && dto.getEnd()!=null) {
            return analyzeService.findActivitiesByProjectIdAndDates(dto.getProject_Id(), dto.getStart(), dto.getEnd());
        }
        if(dto.getProject_Id()!=0  && dto.getEmployee_Id()!=0 ){
            return analyzeService.findActivitiesByProjectIdAndEmployeeId(dto.getProject_Id(),dto.getEmployee_Id());

        }

        if( dto.getStart()!=null && dto.getEnd()!=null && dto.getEmployee_Id()!=0 ){
            return analyzeService.findActivitiesByEmployee_idAndDates(dto.getEmployee_Id(),dto.getStart(),dto.getEnd());

        }
        if(dto.getProject_Id()!=0  ){
            return analyzeService.findActivitiesByProjectId(dto.getProject_Id());

        }
        if( dto.getStart()!=null && dto.getEnd()!=null  ){
            return analyzeService.findActivitiesByDates(dto.getStart(),dto.getEnd());

        }
        if( dto.getEmployee_Id()!=0 ){
            return analyzeService.findActivitiesByEmployee_id(dto.getEmployee_Id());

        }




        return null;
    }








}
