package com.example.trs.controller;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.dto.DateDTO;
import com.example.trs.dto.EmployeeDTO;
import com.example.trs.dto.LoginDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Activity;
import com.example.trs.model.Employee;
import com.example.trs.service.ActivityService;
import com.example.trs.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

;

@RestController
@RequestMapping(value = "activity")
@CrossOrigin(origins="http://localhost:4200")
public class ActivityController {
    @Autowired
    ActivityService activityService;



    @PostMapping("add")
    void   addActivity(@RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityAlreadyExistsException {

        activityService.addActivity(dto);
    }

    @PostMapping("dayactivities/{id}")
    List<Activity>   dayActivities(@RequestBody DateDTO dto, @PathVariable("id") int id)  {

        return activityService.findActivitiesByEmployeeIdAndDate(id, LocalDate.of(dto.getYear(),dto.getMonth(), dto.getDay()));
    }

    @GetMapping("/{id}")
    List<Activity>   activitiesById( @PathVariable("id") int id)  {
        System.out.println("in id ");
        return activityService.findActivitiesByPersonId(id);
    }

    @GetMapping("")
    List<Activity>   getAll()  {
        System.out.println("in getall ");
        return activityService.getAll();
    }







}
