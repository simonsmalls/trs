package com.example.trs.repositories;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.ActivityAlreadyExistsException;
import com.example.trs.exceptions.ActivityNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.service.ActivityService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActivityServiceTest {

    @Autowired
    ActivityService activityService;

    @Test
    @Transactional
    public void addActivityTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(9);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));
        activityDTO.setStartTime(LocalTime.of(14,00,00));
        activityDTO.setEndDate(LocalDate.of(2022,12,22));
        activityDTO.setEndTime(LocalTime.of(15,00,00));
        activityService.addActivity(activityDTO);
        assertEquals(9,activityService.findActivitiesByPersonId(9).get(0).getEmployeeId());
    }

    @Test
    @Transactional
    public void addActivityShouldThrowExceptionIfAlreadyExistTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(1);
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(3);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));
        activityDTO.setStartTime(LocalTime.of(14,00,00));
        activityDTO.setEndDate(LocalDate.of(2022,12,22));
        activityDTO.setEndTime(LocalTime.of(15,00,00));
        assertThrows(ActivityAlreadyExistsException.class, ()-> activityService.addActivity(activityDTO));
    }



    @Test
    @Transactional
    public void editActivityTest() throws ActivityNotFoundException, ProjectNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(1);
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(3);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));
        activityDTO.setStartTime(LocalTime.of(14,00,00));
        activityDTO.setEndDate(LocalDate.of(2022,12,22));
        activityDTO.setEndTime(LocalTime.of(15,00,00));
        activityService.editActivity(activityDTO);
        assertEquals("huh", activityService.findActivitiesByPersonId(3).get(0).getDescription());
    }

    @Test
    public void editActivityThrowsExceptionWhenNotFoundTest() throws ProjectNotFoundException, ActivityNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(987);
        activityDTO.setEmployeeId(987);
        assertThrows(ActivityNotFoundException.class,()-> activityService.editActivity(activityDTO));
    }


}
