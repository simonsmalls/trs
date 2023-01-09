package com.example.trs.controller;

import com.example.trs.dto.ConsultantSalaryDTO;
import com.example.trs.dto.EmployeeDTO;
import com.example.trs.dto.WorkingTimeDTO;
import com.example.trs.exceptions.*;
import com.example.trs.mapper.WorkingTimeMapper;
import com.example.trs.model.Employee;
import com.example.trs.model.WorkingTime;
import com.example.trs.service.WorkingTimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "employees/workingtime")
@CrossOrigin(origins="http://localhost:4200")
public class WorkingTimeController {

    @Autowired
    WorkingTimeService workingTimeService;


    @GetMapping("start/{id}")
    WorkingTimeDTO startClock(@PathVariable("id") int consultantId) throws WrongTypeException, WorkingTimeCannotStartException, JsonProcessingException, EmployeeNotFoundException {

        return WorkingTimeMapper.toDTO(workingTimeService.startWorkingTime(consultantId));
    }

    @GetMapping("end/{id}")
    WorkingTimeDTO endClock(@PathVariable("id") int consultantId) throws WrongTypeException, JsonProcessingException, EmployeeNotFoundException, WorkingTimeCannotEndException {

        return WorkingTimeMapper.toDTO(workingTimeService.endWorkingTime(consultantId));
    }

    @GetMapping("{id}")
    List<WorkingTimeDTO> getWorkingTimesTodayForConsultant(@PathVariable("id") int consultantId) throws JsonProcessingException, EmployeeNotFoundException{

        return workingTimeService.getByConsultantIdToday(consultantId).stream().map(WorkingTimeMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("open/{id}")
    WorkingTimeDTO getOpenWorkingTimeTodayForConsultant(@PathVariable("id") int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException {

        return WorkingTimeMapper.toDTO(workingTimeService.getOpenWorkingTimeTodayByConsultantId(consultantId));
    }

    @GetMapping("salaries/{year}/{month}")
    List<ConsultantSalaryDTO> getSalariesOfAllConsultantsForYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException {

        return workingTimeService.getSalariesOfAllConsultantsFor(year, month);
    }

    @DeleteMapping("{id}")
    void deleteWorkingTime(@PathVariable("id") int id) throws WorkingTimeCannotBeDeletedException, JsonProcessingException {
        workingTimeService.deleteWorkingTime(id);
    }

}
