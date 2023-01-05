package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActivityServiceTest {

    @Autowired
    ActivityService activityService;

    @Test
    @Transactional
    public void addActivityTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityNotFoundException, ActivityTimeOverlapsException, EndTimeBeforeStartTimeException, ENdtimeNeededException, StarttimeNeededException, CategoryNeededException, EmployeeNotFoundException, ActivityInThePastException {
        int tableSize = activityService.getAll().size();
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(9);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));

        activityDTO.setStartTime("14:00");
        activityDTO.setEndTime("15:00");
        activityService.addActivity(activityService.check(activityDTO));
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
        assertThrows(ActivityAlreadyExistsException.class, ()-> activityService.addActivity(activityService.check(activityDTO)));
    }

    @Test
    @Transactional              // Simon's case
    public void addActivityThrowsTimeOverlapsExceptionWhenStartTimeBeforeAndEndTimeAfterExistingActivityTest() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDescription("Huge_Activity");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(5);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,27));

        activityDTO.setStartTime("13:00");
        activityDTO.setEndTime("16:00");
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activityDTO));
    }

    @Test
    @Transactional              // inverse of Simon's case
    public void addActivityThrowsTimeOverlapsExceptionWhenStartTimeAfterAndEndTimeBeforeExistingActivityTest() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDescription("Small_Activity");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(5);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,27));

        activityDTO.setStartTime("15:00");
        activityDTO.setEndTime("15:15");
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activityDTO));
    }



    @Test
    @Transactional
    public void editActivityTest() throws ActivityNotFoundException, ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, EndTimeBeforeStartTimeException, ENdtimeNeededException, StarttimeNeededException, CategoryNeededException, EmployeeNotFoundException, ActivityInThePastException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(1);
        activityDTO.setDescription("huh");
        activityDTO.setProjectId(2);
        activityDTO.setEmployeeId(3);
        activityDTO.setCategoryName("Sales");
        activityDTO.setStartDate(LocalDate.of(2022,12,22));

        activityDTO.setStartTime("14:00");
        activityDTO.setEndTime("15:00");
        activityService.editActivity(activityService.check(activityDTO));
        assertEquals("huh", activityService.findActivitiesByPersonId(3).get(0).getDescription());
    }

    @Test
    public void editActivityThrowsExceptionWhenNotFoundTest() throws ProjectNotFoundException, ActivityNotFoundException {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(987);
        activityDTO.setEmployeeId(987);
        assertThrows(ActivityDoesNotExistsException.class,()-> activityService.editActivity(activityService.check(activityDTO)));
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
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.editActivity(activityService.check(activityDTO)));
    }


}
