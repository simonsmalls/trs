package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityDoesNotExistsException;
import com.example.trs.exceptions.ActivityInThePastException;
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

    @Autowired
    EmployeeService employeeService;

    @Override
    public Activity addActivity(Activity activity) throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException {
        Activity act=activityJpaRepo.findActivityById(activity.getId());
        if(act!=null) {
            throw new ActivityAlreadyExistsException("activiteit bestaat al");
        }
        this.checkTimeOverlap(activity);
        return activityJpaRepo.save(activity);
    }

    @Override

    public Activity editActivity(Activity activity) throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException {

        Activity act=activityJpaRepo.findActivityById(activity.getId());
        if(act==null) {
            throw new ActivityDoesNotExistsException("activiteit bestaat niet");
        }



        this.checkTimeOverlap(activity);
        return activityJpaRepo.save(activity);
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

    @Override
    public Activity check(ActivityDTO dto) throws ProjectNotFoundException, ENdtimeNeededException, CategoryNeededException, EmployeeNotFoundException, StarttimeNeededException, EndTimeBeforeStartTimeException {
        if(dto.getProjectId()==0 ) throw new ProjectNotFoundException("activiteit heeft een project nodig");
        if(dto.getCategoryName()==null) throw new CategoryNeededException("activiteit heeft een category nodig");
        if(dto.getEndTime()==null) throw new ENdtimeNeededException("activiteit heeft eind tijd nodig");
        if(dto.getStartTime()==null ) throw new StarttimeNeededException("activiteir heeft start tijd nodig");
        if(dto.getEmployeeId()==0) throw new EmployeeNotFoundException("activiteit heeft werknemer nodig");

       Activity activity=  activityDTOMapping(dto);
       if(activity.getEndTime().isBefore(activity.getStartTime()))throw new EndTimeBeforeStartTimeException("eindtijd kan niet voor start tijd zijn");
       return activity;
    }

    private void checkTimeOverlap(Activity activity) throws ActivityTimeOverlapsException {
        List<Activity> foundActivityList = activityJpaRepo.findActivitiesByEmployee_idAndDate(activity.getEmployee_id(), activity.getStartDate());
        for (Activity act : foundActivityList) {
            if (!(activity.getStartTime().isAfter(act.getEndTime()) || activity.getEndTime().isBefore(act.getStartTime()))) {
                if (activity.getId() != act.getId()) {
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

