package com.example.trs.service;


import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.model.Activity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityService {

    Activity addActivity(Activity activity) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ProjectAlreadyEndedException;
    Activity editActivity(Activity activity) throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ProjectAlreadyEndedException;


    Activity addActivity(Activity activity) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, InThePastException;
    Activity editActivity(Activity activity) throws ProjectNotFoundException, ActivityDoesNotExistException, ActivityTimeOverlapsException, InThePastException;
    List<Activity> findActivitiesByPersonId(int personId);
    List<Activity> findActivitiesByProjectAfterDate(int projectId, LocalDate date);
    List<Activity> getAll();
    List<Activity> findActivitiesByEmployeeIdAndDate(int personId, LocalDate date);
    void deleteById(int id) throws ActivityDoesNotExistException, InThePastException;
    Activity findActivityById(int id) throws ActivityDoesNotExistException;

    int calculateTimeSpent(LocalTime startTime, LocalTime endTime);

    Activity check(ActivityDTO dto) throws ProjectNotFoundException, EndTimeNeededException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, WrongTimeException, DateRequiredException;



    List<Activity> findActivitiesForProjectOfMonth(int projectId, LocalDate startDate, LocalDate endDate);




}
