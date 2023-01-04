package com.example.trs.controller;

import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.WorkingTimeCannotEndException;
import com.example.trs.exceptions.WorkingTimeCannotStartException;
import com.example.trs.exceptions.WrongTypeException;
import com.example.trs.model.WorkingTime;
import com.example.trs.service.WorkingTimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "employees/workingtime")
@CrossOrigin(origins="http://localhost:4200")
public class ConsultantController {

    @Autowired
    WorkingTimeService workingTimeService;


    @GetMapping("start/{id}")
    WorkingTime startClock(@PathVariable("id") int consultantId) throws WrongTypeException, WorkingTimeCannotStartException, JsonProcessingException, EmployeeNotFoundException {

        return workingTimeService.startWorkingTime(consultantId);
    }

    @GetMapping("end/{id}")
    WorkingTime endClock(@PathVariable("id") int consultantId) throws WrongTypeException, JsonProcessingException, EmployeeNotFoundException, WorkingTimeCannotEndException {

        return workingTimeService.endWorkingTime(consultantId);
    }

    @GetMapping("{id}")
    List<WorkingTime> getWorkingTimesTodayForConsultant(@PathVariable("id") int consultantId) throws JsonProcessingException, EmployeeNotFoundException{

        return workingTimeService.getByConsultantIdToday(consultantId);
    }

    @GetMapping("open/{id}")
    WorkingTime getOpenWorkingTimeTodayForConsultant(@PathVariable("id") int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException {

        return workingTimeService.getOpenWorkingTimeTodayByConsultantId(consultantId);
    }


}
