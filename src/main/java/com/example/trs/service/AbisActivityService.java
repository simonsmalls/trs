package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.dto.EmployeeDTO;
import com.example.trs.exceptions.*;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityDoesNotExistsException;
import com.example.trs.exceptions.ActivityInThePastException;
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


    @Autowired
    ActivityJpaRepo activityJpaRepo;

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    EmployeeService employeeService;



    @Override
    public Activity addActivity(ActivityDTO activityDTO) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException {
        Activity act=activityJpaRepo.findActivityById(activityDTO.getId());
        if(act!=null) {
            throw new ActivityAlreadyExistsException("activiteit bestaat al");
        }
        this.checkTimeOverlap(activityDTO);
        return activityJpaRepo.save(this.activityDTOMapping(activityDTO));
    }

    @Override

    public Activity editActivity(ActivityDTO activityDTO) throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException {

        Activity act=activityJpaRepo.findActivityById(activityDTO.getId());
        if(act==null) {
            throw new ActivityDoesNotExistsException("activiteit bestaat niet");
        }
        this.checkTimeOverlap(activityDTO);
        return activityJpaRepo.save(this.activityDTOMapping(activityDTO));
    }


    @Override
    public List<Activity> findActivitiesByPersonId(int personId) {
        return activityJpaRepo.findActivitiesForPerson(personId);
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

    @Override
    public void deleteById(int id) throws ActivityDoesNotExistsException, ActivityInThePastException {
        Activity activity=findActivityByid(id);
        if (activity.getStartDate().isBefore(LocalDate.now())) throw new ActivityInThePastException("kan geen activiteiten in het verleden verwijderen");
        activityJpaRepo.delete(activity);
    }

    @Override
    public Activity findActivityByid(int id) throws ActivityDoesNotExistsException {
        Activity activity= activityJpaRepo.findActivityById(id);
        if(activity==null) {
            throw new ActivityDoesNotExistsException("deze activiteit bestaat niet");
        }
        return  activity;
    }

    private void checkTimeOverlap(ActivityDTO activityDTO) throws ActivityTimeOverlapsException {
        List<Activity> foundActivityList = activityJpaRepo.findActivitiesByEmployee_idAndDate(activityDTO.getEmployeeId(), activityDTO.getStartDate());
        for (Activity activity : foundActivityList) {
            if (!(activityDTO.getStartTime().isAfter(activity.getEndTime()) || activityDTO.getEndTime().isBefore(activity.getStartTime()))) {
                if (activityDTO.getId() != activity.getId()) {
                    throw new ActivityTimeOverlapsException("Tijd overlapt met bestaande activiteit");
                }
            }
        }
    }

    private Activity activityDTOMapping(ActivityDTO activityDTO) throws ProjectNotFoundException {

        Project project = projectService.getProjectById(activityDTO.getProjectId());
        Category category = categoryService.findCategoryByName(activityDTO.getCategoryName());

        return ActivityMapper.activityDTOtoActivity(activityDTO, project, category);
    }


}

