package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityDoesNotExistsException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.mapper.ActivityMapper;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Project;
import com.example.trs.repositories.ActivityJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    //todo check if there is already an activity at this time
    public Activity addActivity(ActivityDTO activityDTO) throws ProjectNotFoundException, ActivityAlreadyExistsException {
        Activity act=activityJpaRepo.findActivityById(activityDTO.getId());
        if(act!=null) {
            throw new ActivityAlreadyExistsException("activiteit bestaat al");
        }


        Project project = projectService.getProjectById(activityDTO.getProjectId());
        Category category = categoryService.findCategoryByName(activityDTO.getCategoryName());
        Activity activity = ActivityMapper.activityDTOtoActivity(activityDTO, project, category);
        return activityJpaRepo.save(activity);
    }

    @Override
    //todo check if there is already an activity at this time
    public Activity editActivity(ActivityDTO activityDTO) throws ProjectNotFoundException, ActivityDoesNotExistsException {

        Activity act=activityJpaRepo.findActivityById(activityDTO.getId());
        if(act==null) {
            throw new ActivityDoesNotExistsException("activiteit bestaat niet");
        }
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

    @Override
    public List<Activity> getAll() {
        return activityJpaRepo.findAll();
    }

    @Override
    public List<Activity> findActivitiesByEmployeeIdAndDate(int personId, LocalDate date) {
        return activityJpaRepo.findActivitiesByEmployee_idAndDate(personId,date);
    }


}

