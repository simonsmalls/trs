package com.example.trs.mapper;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Project;



public class ActivityMapper {

    public static Activity activityDTOtoActivity(ActivityDTO activityDTO, Project project, Category category) {

        Activity activity = new Activity();

        if (activityDTO.getDescription()!=null) {
            activity.setDescription(activityDTO.getDescription());
        }
        activity.setEmployee_id(activityDTO.getEmployeeId());
        activity.setProject(project);
        activity.setCategory(category);

        activity.setStartDate(activityDTO.getStartDate());
        activity.setStartTime(activityDTO.getStartTime());
        activity.setEndDate(activityDTO.getEndDate());
        activity.setEndTime(activityDTO.getEndTime());

        return activity;
    }
}
