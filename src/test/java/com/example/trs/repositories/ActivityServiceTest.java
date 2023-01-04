package com.example.trs.repositories;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
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
    public void addActivityTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException, ActivityTimeOverlapsException {
        int tableSize = activityService.getAll().size();
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(9);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));

        activityDTO.setStartTime("14:00");
        activityDTO.setEndTime("15:00");
        activityService.addActivity(activityDTO);
        assertEquals(tableSize + 1, activityService.getAll().size());
    }

    @Test
    @Transactional
    public void addActivityShouldThrowExceptionIfAlreadyExistTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(1);
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(3);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));

        activityDTO.setStartTime("14:00");
        activityDTO.setEndTime("15:00");
        assertThrows(ActivityAlreadyExistsException.class, ()-> activityService.addActivity(activityDTO));
    }



    @Test
    @Transactional
    public void editActivityTest() throws ActivityNotFoundException, ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(1);
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(3);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));

        activityDTO.setStartTime("14:00");
        activityDTO.setEndTime("15:00");
        activityService.editActivity(activityDTO);
        assertEquals("huh", activityService.findActivitiesByPersonId(3).get(0).getDescription());
    }

    @Test
    public void editActivityThrowsExceptionWhenNotFoundTest() throws ProjectNotFoundException, ActivityNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(987);
        activityDTO.setEmployeeId(987);
        assertThrows(ActivityDoesNotExistsException.class,()-> activityService.editActivity(activityDTO));
    }

    @Test
    @Transactional
    public void editActivityThrowsExceptionWhenTimeOverlapTest() throws ActivityNotFoundException, ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(8);
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(1);
        activityDTO.setEmployeeId(11);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2023,12,26));

        activityDTO.setStartTime("12:30");
        activityDTO.setEndTime("15:00");
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.editActivity(activityDTO));
    }


}
