package com.example.trs.service;


import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Activity;

import java.util.List;

public interface ActivityService {



    Activity addActivity(ActivityDTO activity) throws ProjectNotFoundException;
    Activity editActivity(ActivityDTO activity) throws ProjectNotFoundException;
    List<Activity> findActivitiesByPersonId(int personId);





}
