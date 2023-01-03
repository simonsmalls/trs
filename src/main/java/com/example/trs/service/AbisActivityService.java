package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.dto.EmployeeDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.mapper.ActivityMapper;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Employee;
import com.example.trs.model.Project;
import com.example.trs.repositories.ActivityJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class AbisActivityService implements ActivityService {



    private String baseUrl = "http://localhost:8080/employees/";

    @Autowired
    private RestTemplate rt;

    @Autowired
    ActivityJpaRepo activityJpaRepo;

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    EmployeeService employeeService;



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
    public List<Activity> findActivitiesForProjectOfMonth(int projectId, LocalDate startDate, LocalDate endDate) {
        return activityJpaRepo.findActivitiesForProjectOfMonth(projectId, startDate ,endDate);
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

