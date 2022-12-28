package com.example.trs.controller;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.service.ActivityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "activities")
@CrossOrigin(origins="http://localhost:4200")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/add")
    void addActivity(@RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityAlreadyExistsException {
       activityService.addActivity(dto);
    }

    @PostMapping("/edit")
    void editActivity(@RequestBody ActivityDTO dto) throws ProjectNotFoundException, ActivityNotFoundException {
        activityService.editActivity(dto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getActivitiesByID(@PathVariable("id") int id) throws ActivityNotFoundException, ProjectNotFoundException {
        List<ActivityDTO> activityDTOList = activityService.findActivitiesByPersonId(id);
        return new ResponseEntity<>(activityDTOList, HttpStatus.OK);

    }


}
