package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.model.Activity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActivityServiceTest {

    @Autowired
    ActivityService activityService;

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    Activity activity = new Activity();
    ActivityDTO dto = new ActivityDTO();

    @BeforeEach
    void sepUp() throws ProjectNotFoundException {
        activity.setDescription("huh");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(9);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.of(2022,12,22));
        activity.setStartTime(LocalTime.of(14, 0));
        activity.setEndTime(LocalTime.of(15, 0));

        dto.setId(30);
        dto.setEmployeeId(1);
        dto.setProjectId(2);
        dto.setCategoryName("Teaching");
        dto.setDescription("decrypt");
        dto.setStartDate(LocalDate.now());
        dto.setStartTime("14:00");
        dto.setEndTime("15:00");
    }

    @Test
    @Transactional
    public void addActivityTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException {
        int tableSize = activityService.getAll().size();
        activityService.addActivity(activity);
        assertEquals(tableSize + 1, activityService.getAll().size());
    }

    @Test
    @Transactional
    public void addActivityShouldThrowAlreadyExistsExceptionIfAlreadyExistTest() throws ProjectNotFoundException {
        activity.setId(1);
        activity.setEmployee_id(3);
        assertThrows(ActivityAlreadyExistsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // Simon's case
    public void addActivityThrowsTimeOverlapsExceptionWhenStartTimeBeforeAndEndTimeAfterExistingActivityTest() throws ProjectNotFoundException {
        activity.setDescription("Huge_Activity");
        activity.setEmployee_id(5);
        activity.setStartDate(LocalDate.of(2022,12,27));
        activity.setStartTime(LocalTime.of(13, 0));
        activity.setEndTime(LocalTime.of(16, 0));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // inverse of Simon's case
    public void addActivityThrowsTimeOverlapsExceptionWhenStartTimeAfterAndEndTimeBeforeExistingActivityTest() throws ProjectNotFoundException {
        activity.setDescription("Small_Activity");
        activity.setEmployee_id(5);
        activity.setStartDate(LocalDate.of(2022,12,27));
        activity.setStartTime(LocalTime.of(15, 0));
        activity.setEndTime(LocalTime.of(15, 15));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional
    public void editActivityTest() throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException {
        activity.setId(1);
        activity.setEmployee_id(3);
        activityService.editActivity(activity);
        assertEquals("huh", activityService.findActivitiesByPersonId(3).get(0).getDescription());
    }

    @Test
    public void editActivityThrowsActivityDoesNotExistExceptionWhenNotFoundTest() {
        activity.setId(987);
        activity.setEmployee_id(987);
        assertThrows(ActivityDoesNotExistsException.class,()-> activityService.editActivity(activity));
    }

    @Test
    @Transactional
    public void editActivityThrowsExceptionWhenTimeOverlapTest() throws ProjectNotFoundException {
        activity.setId(8);
        activity.setProject(projectService.getProjectById(1));
        activity.setEmployee_id(11);
        activity.setStartDate(LocalDate.of(2023,12,26));
        activity.setStartTime(LocalTime.of(12, 30));
        activity.setEndTime(LocalTime.of(15, 0));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.editActivity(activity));
    }

    @Test
    @Transactional
    void checkActivityDTOTest() throws ProjectNotFoundException, WrongTimeException, EndTimeNeededException, StartTimeNeededException, CategoryNeededException, EmployeeNotFoundException, DateRequiredException {
        assertDoesNotThrow( ()-> activityService.check(dto));
        assertEquals("decrypt", activityService.check(dto).getDescription());
        assertEquals(1, activityService.check(dto).getCategory().getId());
        assertEquals(2, activityService.check(dto).getProject().getId());
        assertEquals(1, activityService.check(dto).getProject().getClient().getId());
        assertEquals(LocalTime.of(14, 0), activityService.check(dto).getStartTime());
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsProjectNotFoundExceptionTest() {
        dto.setProjectId(0);
        assertThrows(ProjectNotFoundException.class, ()-> activityService.check(dto));
        dto.setProjectId(-5);
        assertThrows(ProjectNotFoundException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsCategoryNeededExceptionTest() {
        dto.setCategoryName(null);
        assertThrows(CategoryNeededException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsDateRequiredExceptionTest() {
        dto.setStartDate(null);
        assertThrows(DateRequiredException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsEndTimeNeededExceptionTest() {
        dto.setEndTime(null);
        assertThrows(EndTimeNeededException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsStartTimeNeededExceptionTest() {
        dto.setStartTime(null);
        assertThrows(StartTimeNeededException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsEmployeeNotFoundExceptionTest() {
        dto.setEmployeeId(0);
        assertThrows(EmployeeNotFoundException.class, ()-> activityService.check(dto));
        dto.setEmployeeId(-5);            // TODO currently no check on non-existing employee id > 0
        assertThrows(EmployeeNotFoundException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsEndTimeBeforeStartTimeWrongTimeExceptionTest() {
        dto.setStartTime("15:01");
        dto.setEndTime("15:00");
        assertThrows(WrongTimeException.class, ()-> activityService.check(dto));
    }

    @Test
    @Transactional
    void checkActivityDTOThrowsSameEndTimeAndStartTimeWrongTimeExceptionTest() {
        dto.setStartTime("15:26");
        dto.setEndTime("15:26");
        assertThrows(WrongTimeException.class, ()-> activityService.check(dto));
    }




}
