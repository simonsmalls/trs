package com.example.trs.controller;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.dto.DateDTO;
import com.example.trs.exceptions.*;
import com.example.trs.mapper.ActivityMapper;
import com.example.trs.model.Activity;
import com.example.trs.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
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
    void   addActivity( @RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, EndTimeBeforeStartTimeException, EndTimeNeededException, StartTimeNeededException, CategoryNeededException, EmployeeNotFoundException, DateRequiredException {

        activityService.addActivity(activityService.check(dto));
    }

    @PostMapping("edit")
    void   editActivity( @RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, EndTimeBeforeStartTimeException, EndTimeNeededException, StartTimeNeededException, CategoryNeededException, EmployeeNotFoundException, DateRequiredException {

        activityService.editActivity(activityService.check(dto));
    }

    @PostMapping("dayactivities/{id}")
    List<ActivityDTO>   dayActivities(@RequestBody DateDTO dto, @PathVariable("id") int id)  {

        List<Activity> list=  activityService.findActivitiesByEmployeeIdAndDate(id, LocalDate.of(dto.getYear(),dto.getMonth(), dto.getDay()));
        return list.stream().map(ActivityMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/person/{id}")
    List<ActivityDTO>   activitiesById( @PathVariable("id") int id)  {

        List<Activity> list=  activityService.findActivitiesByPersonId(id);
        return list.stream().map(ActivityMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ActivityDTO   activityById( @PathVariable("id") int id) throws ActivityDoesNotExistsException {

        Activity  activity=  activityService.findActivityById(id);
        return ActivityMapper.toDTO(activity);
    }

    @DeleteMapping("/{id}")
    void deleteActivityById(@PathVariable("id") int id) throws ActivityDoesNotExistsException, ActivityInThePastException {
        activityService.deleteById(id);
    }

    @GetMapping("")
    List<ActivityDTO>  getAll()  {

        List<Activity> list=  activityService.getAll();
        return list.stream().map(ActivityMapper::toDTO).collect(Collectors.toList());
    }







}
