package com.example.trs.service;

import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.WorkingTimeCannotEndException;
import com.example.trs.exceptions.WorkingTimeCannotStartException;
import com.example.trs.exceptions.WrongTypeException;
import com.example.trs.model.WorkingTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WorkingTimeServiceTest {

    @Autowired
    WorkingTimeService workingTimeService;


    @Transactional(rollbackOn = Throwable.class)
    @Test
    void startingWorkingTimeOfConsultantWithOpenWorkingTimeThrowsException() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException, JsonProcessingException {
        workingTimeService.startWorkingTime(9);
        assertThrows(WorkingTimeCannotStartException.class, () -> workingTimeService.startWorkingTime(9));
    }

    @Transactional
    @Test
    void startingWorkingTimeForConsultantWithoutOpenWorks() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException, JsonProcessingException {
        WorkingTime time = workingTimeService.startWorkingTime(3);
        assertEquals(3, time.getConsultant().getId());
        assertNull(time.getEndTime());
        assertEquals(0, time.getTimeWorkedMin());
    }

    @Transactional
    @Test
    void startWorkingTimeForNonConsultantThrowsException() {
        assertThrows(WrongTypeException.class, () -> workingTimeService.startWorkingTime(7));
    }

    @Transactional
    @Test
    void startWorkingTimeForNonExistingEmployeeThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> workingTimeService.startWorkingTime(1000));
    }

    @Test
    void endWorkingTimeForConsultantWithOpenWorks() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException, WorkingTimeCannotEndException, JsonProcessingException {
        WorkingTime start = workingTimeService.startWorkingTime(3);
        WorkingTime end = workingTimeService.endWorkingTime(3);
        assertEquals(start.getId(), end.getId());
        assertEquals(start.getStartTime(), end.getStartTime());
        assertEquals(start.getConsultant().getId(), end.getConsultant().getId());
        assertEquals(start.getDate(), end.getDate());
    }

    @Test
    void endWorkingTimeOfConsultantWithoutOpenWorkingTimeThrowsException() throws WrongTypeException, WorkingTimeCannotEndException, EmployeeNotFoundException, WorkingTimeCannotEndException, JsonProcessingException, WorkingTimeCannotStartException {
        WorkingTime start = workingTimeService.startWorkingTime(9);
        WorkingTime end = workingTimeService.endWorkingTime(9);
        assertThrows(WorkingTimeCannotEndException.class, () -> workingTimeService.endWorkingTime(9));
    }

    @Transactional
    @Test
    void endWorkingTimeForNonConsultantThrowsException() {
        assertThrows(WrongTypeException.class, () -> workingTimeService.endWorkingTime(7));
    }

    @Transactional
    @Test
    void endWorkingTimeForNonExistingEmployeeThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> workingTimeService.endWorkingTime(1000));
    }
}
