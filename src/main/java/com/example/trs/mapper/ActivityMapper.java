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

    public static ActivityDTO toDTO(Activity activity,  String employeeName) {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(activity.getId());
        activityDTO.setDescription(activity.getDescription());
        activityDTO.setEmployeeId(activity.getEmployee_id());
        activityDTO.setProjectId(activity.getProject().getId());
        activityDTO.setProjectName(activity.getProject().getName());
        activityDTO.setEmployeeName(employeeName);
        activityDTO.setCategoryName(activity.getCategory().getName());
        activityDTO.setStartDate(activity.getStartDate());
        activityDTO.setStartTime(activity.getStartTime());
        activityDTO.setEndDate(activity.getEndDate());
        activityDTO.setEndTime(activity.getEndTime());
        activityDTO.setTimeSpent(activity.getTimeSpent());

         return activityDTO;
    }
}
