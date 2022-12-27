package com.example.trs.service;

import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.WorkingTimeCannotStartException;
import com.example.trs.exceptions.WrongTypeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WorkingTimeServiceTest {

    @Autowired
    WorkingTimeService workingTimeService;

    @Test
    void startingWorkingTimeOfConsultantWithOpenWorkingTimeThrowsException(){
        assertThrows(WorkingTimeCannotStartException.class, () -> workingTimeService.startWorkingTime(9));
    }

    //@Transactional
    @Test
    void startingWorkingTimeForConsultantWithoutOpenWorks() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException, JsonProcessingException {
        workingTimeService.startWorkingTime(3);
        //WorkingTime time = workingTimeService.startWorkingTime(3);
        //assertEquals(3, time.getConsultant().getId());
        //assertNull(time.getEndTime());
        //assertEquals(0, time.getTimeWorkedMin());
    }

    @Test
    void startWorkingTimeForNonConsultantThrowsException() {
        assertThrows(WrongTypeException.class, () -> workingTimeService.startWorkingTime(7));
    }

    @Test
    void startWorkingTimeForNonExistingEmployeeThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> workingTimeService.startWorkingTime(1000));
    }
}
