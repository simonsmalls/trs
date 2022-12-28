package com.example.trs.service;


import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityDoesNotExistsException;
import com.example.trs.exceptions.ActivityInThePastException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {



    Activity addActivity(ActivityDTO activity) throws ProjectNotFoundException, ActivityAlreadyExistsException;
    Activity editActivity(ActivityDTO activity) throws ProjectNotFoundException, ActivityDoesNotExistsException;
    List<Activity> findActivitiesByPersonId(int personId);
    List<Activity> getAll();
    List<Activity> findActivitiesByEmployeeIdAndDate(int personId, LocalDate date);
    void deleteById(int id) throws ActivityDoesNotExistsException, ActivityInThePastException;
    Activity findActivityByid(int id) throws ActivityDoesNotExistsException;







}
