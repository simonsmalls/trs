package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.mapper.ActivityMapper;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Project;
import com.example.trs.repositories.ActivityJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class AbisActivityService implements ActivityService {

    @Autowired
    ActivityJpaRepo activityJpaRepo;

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;



    @Override
    public Activity addActivity(ActivityDTO activityDTO) {
        Project project = projectService.getProjectById(activityDTO.getProjectId());
        Category category = categoryService.findCategoryByName(activityDTO.getCategoryName());
        Activity activity = ActivityMapper.activityDTOtoActivity(activityDTO, project, category);
        return activityJpaRepo.save(activity);
    }

    @Override
    public Activity editActivity(ActivityDTO activityDTO) {
        Project project = projectService.getProjectById(activityDTO.getProjectId());
        Category category = categoryService.findCategoryByName(activityDTO.getCategoryName());
        Activity activity = ActivityMapper.activityDTOtoActivity(activityDTO, project, category);
        if (activityJpaRepo.findActivityByEmployeeProjectCategory(
                activity.getEmployee_id(), activity.getProject().getId(), activity.getCategory().getId(),
                activity.getStartDate(), activity.getStartTime(), activity.getEndDate(), activity.getEndTime())!=null) {
            return activityJpaRepo.save(activity);
        }
        return null;
    }


   @Override
    public List<Activity> findActivitiesByPersonId(int personId) {
        return activityJpaRepo.findActivitiesForPerson(personId);
    }


}

