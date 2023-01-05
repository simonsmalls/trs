package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.model.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
        activity.setEmployee_id(1);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.now());
        activity.setStartTime(LocalTime.of(14, 0));
        activity.setEndTime(LocalTime.of(15, 0));

        dto.setId(activityService.getAll().size() +1);
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
    public void addActivityTest() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ProjectAlreadyEndedException {
        int tableSize = activityService.getAll().size();
        activityService.addActivity(activity);
        assertEquals(tableSize + 1, activityService.getAll().size());
    }

    @Test
    @Transactional
    public void addActivityOnDayWhereProjectEndsThrowsException() throws ProjectNotFoundException, ActivityAlreadyExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ProjectAlreadyEndedException {
        activity.setStartDate(LocalDate.of(2023,1,30));
        assertThrows(ProjectAlreadyEndedException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional
    public void addActivityShouldThrowAlreadyExistsExceptionIfAlreadyExistTest() {
        activity.setId(1);
        activity.setEmployee_id(3);
        assertThrows(ActivityAlreadyExistsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // Simon's case
    public void addActivityThatEncapsulatesExistingActivityThrowsException() throws ProjectNotFoundException, ActivityInThePastException, ActivityTimeOverlapsException, ActivityAlreadyExistsException, WrongTimeException, EndTimeNeededException, DateRequiredException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        activity = activityService.check(dto);   // creates new Activity() object
        //System.out.println(activity.getId());
        activity.setDescription("Huge_Activity");
        activity.setStartTime(LocalTime.of(13, 0));
        activity.setEndTime(LocalTime.of(16, 0));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // inverse of Simon's case
    public void addActivityThatIsBetweenExistingActivityThrowsException() throws ProjectNotFoundException, ActivityInThePastException, ActivityTimeOverlapsException, ActivityAlreadyExistsException, WrongTimeException, EndTimeNeededException, DateRequiredException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        activity = activityService.check(dto);   // creates new Activity() object
        activity.setDescription("Small_Activity");
        activity.setStartTime(LocalTime.of(14, 30));
        activity.setEndTime(LocalTime.of(14, 45));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional              // activity half overlap
    public void addActivityStartsBeforeExistingActivityEndsThrowsException() throws ProjectNotFoundException, ActivityInThePastException, ActivityTimeOverlapsException, ActivityAlreadyExistsException, WrongTimeException, EndTimeNeededException, DateRequiredException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        activity = activityService.check(dto);   // creates new Activity() object
        activity.setDescription("Small_Activity");
        activity.setStartTime(LocalTime.of(14, 30));
        activity.setEndTime(LocalTime.of(15, 45));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }
    @Test
    @Transactional              // activity half overlap 2
    public void addActivityEndsInsideExistingActivityThrowsException() throws ProjectNotFoundException, ActivityInThePastException, ActivityTimeOverlapsException, ActivityAlreadyExistsException, WrongTimeException, EndTimeNeededException, DateRequiredException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        activity = activityService.check(dto);   // creates new Activity() object
        activity.setDescription("Small_Activity");
        activity.setStartTime(LocalTime.of(13, 30));
        activity.setEndTime(LocalTime.of(14, 5));
        assertThrows(ActivityTimeOverlapsException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional
    void addActivityThrowsActivityInThePastExceptionTest() {
        activity.setStartDate(LocalDate.of(1000, 1, 1));
        assertThrows(ActivityInThePastException.class, ()-> activityService.addActivity(activity));
    }

    @Test
    @Transactional
    void editActivityThrowsActivityInThePastExceptionTest() throws ActivityDoesNotExistsException {
        activity = activityService.findActivityById(1);
        assertThrows(ActivityInThePastException.class, ()-> activityService.editActivity(activity));

    }

    @Test
    @Transactional
    public void editActivityTest() throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ActivityAlreadyExistsException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        activity.setDescription("Edited!");
        activityService.editActivity(activity);
        assertEquals("Edited!", activityService.findActivityById(activity.getId()).getDescription());
    }
    @Test
    @Transactional
    public void editActivitySoThatItIsAfterProjectEndDateThrowsException() throws ProjectNotFoundException, ActivityDoesNotExistsException, ActivityTimeOverlapsException, ActivityInThePastException, ActivityAlreadyExistsException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        activity.setStartDate(LocalDate.of(2023,2,2));
        assertThrows(ProjectAlreadyEndedException.class, () -> activityService.editActivity(activity));
    }

    @Test
    @Transactional
    public void editActivityThrowsActivityDoesNotExistExceptionWhenNotFoundTest() {
        activity.setId(987);
        activity.setEmployee_id(987);
        assertThrows(ActivityDoesNotExistsException.class,()-> activityService.editActivity(activity));
    }

    @Test
    @Transactional
    public void editActivityThrowsExceptionWhenTimeOverlapTest() throws ProjectNotFoundException, ActivityInThePastException, ActivityTimeOverlapsException, ActivityAlreadyExistsException, WrongTimeException, EndTimeNeededException, DateRequiredException, CategoryNeededException, EmployeeNotFoundException, StartTimeNeededException, ProjectAlreadyEndedException {
        activityService.addActivity(activity);
        dto.setStartTime("15:30");
        dto.setEndTime("16:00");
        activityService.addActivity(activityService.check(dto));

        activity.setStartTime(LocalTime.of(14, 30));
        activity.setEndTime(LocalTime.of(16, 0));
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

    @Test
    @Transactional
    void calculateTimeSpent() {
        assertEquals(60, activityService.calculateTimeSpent(
                LocalTime.of(14, 0), LocalTime.of(15, 0)));
        assertEquals(40, activityService.calculateTimeSpent(
                LocalTime.of(14, 0), LocalTime.of(14, 40)));
        assertEquals(186, activityService.calculateTimeSpent(
                LocalTime.of(13, 59), LocalTime.of(17, 5)));
        assertEquals(1, activityService.calculateTimeSpent(
                LocalTime.of(14, 56), LocalTime.of(14, 57)));
        assertEquals(0, activityService.calculateTimeSpent(
                LocalTime.of(14, 0), LocalTime.of(14, 0)));
        assertEquals(-8, activityService.calculateTimeSpent(
                LocalTime.of(14, 0), LocalTime.of(13, 52)));
    }




}
