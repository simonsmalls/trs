package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.mapper.ActivityMapper;
import com.example.trs.model.Activity;
import com.example.trs.model.Category;
import com.example.trs.model.Project;
import com.example.trs.repositories.ActivityJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    public Activity addActivity(Activity activity) throws ActivityAlreadyExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ProjectAlreadyEndedException {
        Activity act=activityJpaRepo.findActivityById(activity.getId());
        if(act!=null) {
            throw new ActivityAlreadyExistsException("activiteit bestaat al");
        }
        if (activity.getStartDate().isBefore(LocalDate.now())) throw new ActivityInThePastException("kan geen activiteit in het verleden toevoegen");
        if (activity.getProject()!=null && activity.getStartDate().isAfter(activity.getProject().getEndDate()))
            throw new ProjectAlreadyEndedException("dit project loopt op dit datum niet meer");


        this.checkTimeOverlap(activity);
        return activityJpaRepo.save(activity);
    }

    @Override
    public Activity editActivity(Activity activity) throws ActivityDoesNotExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ProjectAlreadyEndedException {

        Activity act=activityJpaRepo.findActivityById(activity.getId());
        if(act==null) {
            throw new ActivityDoesNotExistsException("activiteit bestaat niet");
        }
        if (activity.getStartDate().isBefore(LocalDate.now())) throw new ActivityInThePastException("kan geen activiteit in het verleden aanpassen");
        if (activity.getProject()!=null && activity.getStartDate().isAfter(activity.getProject().getEndDate()))
            throw new ProjectAlreadyEndedException("dit project loopt op dit datum niet meer");

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
        Activity activity= findActivityById(id);
        if (activity.getStartDate().isBefore(LocalDate.now())) throw new ActivityInThePastException("kan geen activiteiten in het verleden verwijderen");
        activityJpaRepo.delete(activity);
    }

    @Override
    public Activity findActivityById(int id) throws ActivityDoesNotExistsException {
        Activity activity= activityJpaRepo.findActivityById(id);
        if(activity==null) {
            throw new ActivityDoesNotExistsException("deze activiteit bestaat niet");
        }
        return  activity;
    }

    @Override
    public int calculateTimeSpent(LocalTime startTime, LocalTime endTime) {
        return (int) startTime.until(endTime, ChronoUnit.MINUTES);
    }

    @Override
    public Activity check(ActivityDTO dto) throws ProjectNotFoundException, EndTimeNeededException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, WrongTimeException, DateRequiredException {
        if(dto.getProjectId() < 0) throw new ProjectNotFoundException("project bestaat niet");
        if(dto.getCategoryName()==null) throw new CategoryNeededException("activiteit heeft een categorie nodig");
        if(dto.getStartDate() == null) throw new DateRequiredException("activiteit heeft een datum nodig");
        if(dto.getEndTime()==null) throw new EndTimeNeededException("activiteit heeft een eindtijd nodig");
        if(dto.getStartTime()==null ) throw new StartTimeNeededException("activiteit heeft een starttijd nodig");
        if(dto.getStartTime().equals(dto.getEndTime())) throw new WrongTimeException("eindtijd moet verschillen van starttijd");
        if(dto.getEmployeeId() <= 0) throw new EmployeeNotFoundException("activiteit heeft een werknemer nodig");
        // TODO check for existing employee in DB
        // Project Name not required, it will be overwritten by Mapping anyway, which maps on Project ID

       Activity activity=  activityDTOMapping(dto);
       if(activity.getEndTime().isBefore(activity.getStartTime())) throw new WrongTimeException("eindtijd kan niet voor start tijd zijn");
       activity.setTimeSpent(calculateTimeSpent(activity.getStartTime(), activity.getEndTime()));
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

    @Override
    public List<Activity> findActivitiesByProjectAfterDate(int projectId, LocalDate date) {
        return activityJpaRepo.findActivitiesByProjectIdAfterDate(projectId, date);
    }
}

