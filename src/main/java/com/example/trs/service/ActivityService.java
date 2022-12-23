package com.example.trs.service;


import com.example.trs.dto.ActivityDTO;
import com.example.trs.model.Activity;

import java.util.List;

public interface ActivityService {



    Activity addActivity(ActivityDTO activity);
    Activity editActivity(ActivityDTO activity);
    List<Activity> findActivitiesByPersonId(int personId);





}
