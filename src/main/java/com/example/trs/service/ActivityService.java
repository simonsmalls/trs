package com.example.trs.service;


import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Activity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ActivityService {



    void addActivity(ActivityDTO activity) throws ProjectNotFoundException, ActivityAlreadyExistsException;
    void editActivity(ActivityDTO activity) throws ActivityNotFoundException, ProjectNotFoundException;
    List<ActivityDTO> findActivitiesByPersonId(int personId) throws ActivityNotFoundException, ProjectNotFoundException;

    List<Activity> findActivitiesForProjectOfMonth(int projectId, LocalDate startDate, LocalDate endDate);

    List<Activity> getAll();


}
