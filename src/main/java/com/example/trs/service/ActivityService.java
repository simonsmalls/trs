package com.example.trs.service;


import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {



    Activity addActivity(Activity activity) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, ActivityInThePastException;
    Activity editActivity(Activity activity) throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, ActivityInThePastException;
    List<Activity> findActivitiesByPersonId(int personId);
    List<Activity> getAll();
    List<Activity> findActivitiesByEmployeeIdAndDate(int personId, LocalDate date);
    void deleteById(int id) throws ActivityDoesNotExistsException, ActivityInThePastException;
    Activity findActivityByid(int id) throws ActivityDoesNotExistsException;
    Activity check(ActivityDTO dto) throws ProjectNotFoundException, ENdtimeNeededException, CategoryNeededException, EmployeeNotFoundException, StarttimeNeededException, EndTimeBeforeStartTimeException;



    List<Activity> findActivitiesForProjectOfMonth(int projectId, LocalDate startDate, LocalDate endDate);




}
