package com.example.trs.service;

import com.example.trs.exceptions.*;
import com.example.trs.model.Activity;
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

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @Test
    @Transactional
    public void addActivityTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException {
        int tableSize = activityService.getAll().size();
        Activity activity = new Activity();
        activity.setDescription("huh");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(9);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2022,12,22));

        activity.setStartTime(LocalTime.of(14, 0));
        activity.setEndTime(LocalTime.of(15, 0));
        activityService.addActivity(activity);
        assertEquals(tableSize + 1, activityService.getAll().size());
    }

    @Test
    @Transactional
    public void addActivityShouldThrowAlreadyExistsExceptionIfAlreadyExistTest() throws ProjectNotFoundException {
        Activity activity = new Activity();
        activity.setId(1);
        activity.setDescription("huh");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(3);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2022,12,22));

        activity.setStartTime(LocalTime.of(14, 0));
        activity.setEndTime(LocalTime.of(15, 0));
        assertThrows(ActivityAlreadyExistsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // Simon's case
    public void addActivityThrowsTimeOverlapsExceptionWhenStartTimeBeforeAndEndTimeAfterExistingActivityTest() throws ProjectNotFoundException {
        Activity activity = new Activity();
        activity.setDescription("Huge_Activity");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(5);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2022,12,27));

        activity.setStartTime(LocalTime.of(13, 0));
        activity.setEndTime(LocalTime.of(16, 0));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // inverse of Simon's case
    public void addActivityThrowsTimeOverlapsExceptionWhenStartTimeAfterAndEndTimeBeforeExistingActivityTest() throws ProjectNotFoundException {
        Activity activity = new Activity();
        activity.setDescription("Small_Activity");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(5);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2022,12,27));

        activity.setStartTime(LocalTime.of(15, 0));
        activity.setEndTime(LocalTime.of(15, 15));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }



    @Test
    @Transactional
    public void editActivityTest() throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException {
        Activity activity = new Activity();
        activity.setId(1);
        activity.setDescription("huh");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(3);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2022,12,22));

        activity.setStartTime(LocalTime.of(14, 0));
        activity.setEndTime(LocalTime.of(15, 0));
        activityService.editActivity(activity);
        assertEquals("huh", activityService.findActivitiesByPersonId(3).get(0).getDescription());
    }

    @Test
    public void editActivityThrowsActivityDoesNotExistExceptionWhenNotFoundTest() {
        Activity activity = new Activity();
        activity.setId(987);
        activity.setEmployee_id(987);
        assertThrows(ActivityDoesNotExistsException.class,()-> activityService.editActivity(activity));
    }

    @Test
    @Transactional
    public void editActivityThrowsExceptionWhenTimeOverlapTest() throws ProjectNotFoundException {
        Activity activity = new Activity();
        activity.setId(8);
        activity.setDescription("huh");
        activity.setProject(projectService.getProjectById(1));
        activity.setEmployee_id(11);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2023,12,26));

        activity.setStartTime(LocalTime.of(12, 30));
        activity.setEndTime(LocalTime.of(15, 0));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.editActivity(activity));
    }


}
