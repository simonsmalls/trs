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
    public void addActivity(ActivityDTO activityDTO) throws ProjectNotFoundException, ActivityAlreadyExistsException {
        if (activityJpaRepo.findActivityByEmployeeIdAndActivityId(activityDTO.getEmployeeId(), activityDTO.getActivityId())!=null) {
            throw new ActivityAlreadyExistsException("Activity already exists");
        }
        Project project = projectService.getProjectById(activityDTO.getProjectId());
        Category category = categoryService.findCategoryByName(activityDTO.getCategoryName());
        Activity activity = ActivityMapper.activityDTOtoActivity(activityDTO, project, category);
        activityJpaRepo.save(activity);
    }

    @Override
    public void editActivity(ActivityDTO activityDTO) throws ActivityNotFoundException, ProjectNotFoundException {

        int activityId = activityDTO.getActivityId();
        int personId = activityDTO.getEmployeeId();

        if (activityJpaRepo.findActivityByEmployeeIdAndActivityId(personId,activityId)!=null) {
            Project project = projectService.getProjectById(activityDTO.getProjectId());
            Category category = categoryService.findCategoryByName(activityDTO.getCategoryName());
            Activity mappedActivity = ActivityMapper.activityDTOtoActivity(activityDTO, project, category);
            Activity storedActivity = activityJpaRepo.findActivityByEmployeeIdAndActivityId(personId,activityId);

            storedActivity.setStartTime(mappedActivity.getStartTime());
            storedActivity.setStartDate(mappedActivity.getStartDate());
            storedActivity.setEndTime(mappedActivity.getEndTime());
            storedActivity.setEndDate(mappedActivity.getEndDate());
            storedActivity.setDescription(mappedActivity.getDescription());
            storedActivity.setProject(mappedActivity.getProject());
            storedActivity.setTimeSpent(mappedActivity.getTimeSpent());
            storedActivity.setCategory(mappedActivity.getCategory());

            activityJpaRepo.save(storedActivity);
        } else {
             throw new ActivityNotFoundException("Activity not found");
        }
    }


   @Override
    public List<ActivityDTO> findActivitiesByPersonId(int personId) throws ActivityNotFoundException{
        List<Activity> foundActivities = activityJpaRepo.findActivitiesForPerson(personId);
        ResponseEntity<EmployeeDTO> responseEntity = rt.getForEntity(baseUrl + personId, EmployeeDTO.class);
        String personName = Objects.requireNonNull(responseEntity.getBody()).getFirstName();
        try {
            foundActivities.get(0);
        } catch (RuntimeException e) {
            throw new ActivityNotFoundException("No activities found");
        }

        List<ActivityDTO> foundActivityDTOList = new ArrayList<>();
        for (Activity activity : foundActivities) {
            ActivityDTO activityDTO = ActivityMapper.toDTO(activity, personName);
            foundActivityDTOList.add(activityDTO);

        }
        return foundActivityDTOList;
    }

}

