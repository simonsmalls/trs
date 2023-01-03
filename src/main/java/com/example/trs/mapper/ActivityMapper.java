package com.example.trs.mapper;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Project;



public class ActivityMapper {

    public static Activity activityDTOtoActivity(ActivityDTO activityDTO, Project project, Category category) {

        Activity activity = new Activity();
        activity.setId(activityDTO.getId());

        if (activityDTO.getDescription()!=null) {
            activity.setDescription(activityDTO.getDescription());
        }
        activity.setEmployee_id(activityDTO.getEmployeeId());
        activity.setProject(project);
        activity.setCategory(category);

        activity.setStartDate(activityDTO.getStartDate());
        activity.setStartTime(activityDTO.getStartTime());
        activity.setEndTime(activityDTO.getEndTime());

        return activity;
    }

    public static ActivityDTO toDTO(Activity activity){

        ActivityDTO dto= new ActivityDTO();
        dto.setId(activity.getId());
        dto.setDescription(activity.getDescription());
        dto.setEmployeeId(activity.getEmployee_id());
        dto.setProjectId(activity.getProject().getId());
        dto.setProjectName(activity.getProject().getName());
        //todo employeename

        dto.setCategoryName(activity.getCategory().getName());
        dto.setStartDate(activity.getStartDate());
        dto.setStartTime(activity.getStartTime());
        dto.setEndTime(activity.getEndTime());
        dto.setTimeSpent(activity.getTimeSpent());


        return dto;

    }
}
