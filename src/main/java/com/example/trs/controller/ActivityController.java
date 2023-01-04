package com.example.trs.controller;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.dto.DateDTO;
import com.example.trs.dto.EmployeeDTO;
import com.example.trs.dto.LoginDTO;
import com.example.trs.exceptions.*;
import com.example.trs.mapper.ActivityMapper;
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
import java.util.stream.Collectors;



@RestController
@RequestMapping(value = "activity")
@CrossOrigin(origins="http://localhost:4200")
public class ActivityController {
    @Autowired
    ActivityService activityService;



    @PostMapping("add")
    void   addActivity( @RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, EndTimeBeforeStartTimeException, ENdtimeNeededException, StarttimeNeededException, CategoryNeededException, EmployeeNotFoundException, ActivityInThePastException {


        activityService.addActivity(activityService.check(dto));
    }

    @PostMapping("edit")
    void   editActivity( @RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, EndTimeBeforeStartTimeException, ENdtimeNeededException, StarttimeNeededException, CategoryNeededException, EmployeeNotFoundException, ActivityInThePastException {

        activityService.editActivity(activityService.check(dto));
    }

    @PostMapping("dayactivities/{id}")
    List<ActivityDTO>   dayActivities(@RequestBody DateDTO dto, @PathVariable("id") int id)  {

        List<Activity> list=  activityService.findActivitiesByEmployeeIdAndDate(id, LocalDate.of(dto.getYear(),dto.getMonth(), dto.getDay()));
        return list.stream().map(x-> ActivityMapper.toDTO(x)).collect(Collectors.toList());
    }

    @GetMapping("/person/{id}")
    List<ActivityDTO>   activitiesById( @PathVariable("id") int id)  {

        List<Activity> list=  activityService.findActivitiesByPersonId(id);
        return list.stream().map(x-> ActivityMapper.toDTO(x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ActivityDTO   activityById( @PathVariable("id") int id) throws ActivityDoesNotExistsException {

        Activity  activity=  activityService.findActivityByid(id);
        return ActivityMapper.toDTO(activity);
    }

    @DeleteMapping("/{id}")
    void deleteActivityById(@PathVariable("id") int id) throws ActivityDoesNotExistsException, ActivityInThePastException {
        activityService.deleteById(id);
    }

    @GetMapping("")
    List<ActivityDTO>  getAll()  {

        List<Activity> list=  activityService.getAll();
        return list.stream().map(x-> ActivityMapper.toDTO(x)).collect(Collectors.toList());
    }







}
