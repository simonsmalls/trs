package com.example.trs.mapper;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Project;

import java.time.LocalTime;


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
        int a=Integer.parseInt(activityDTO.getStartTime().substring(0,2));
        int b= Integer.parseInt(activityDTO.getStartTime().substring(3));
        activity.setStartTime(LocalTime.of(a,b));
        int c=Integer.parseInt(activityDTO.getEndTime().substring(0,2));
        int d= Integer.parseInt(activityDTO.getEndTime().substring(3));


        activity.setEndTime(LocalTime.of(c,d));

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
        String hourStart=activity.getStartTime().getHour() >10? ""+activity.getStartTime().getHour():"0"+activity.getStartTime().getHour();
        String hourEnd=activity.getEndTime().getHour() >10? ""+activity.getEndTime().getHour():"0"+activity.getEndTime().getHour();
        String minuteStart=activity.getStartTime().getMinute() >10? ""+activity.getStartTime().getMinute():"0"+activity.getStartTime().getMinute();
        String minuteEnd=activity.getEndTime().getMinute() >10? ""+activity.getEndTime().getMinute():"0"+activity.getEndTime().getMinute();
        dto.setStartTime(hourStart+ ":"+ minuteStart);
        dto.setEndTime(hourEnd + ":"+minuteEnd);
        dto.setTimeSpent(activity.getTimeSpent());


        return dto;

    }
}
