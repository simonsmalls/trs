package com.example.trs.service;

import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.WorkingTimeCannotStartException;
import com.example.trs.exceptions.WorkingTimeCannotEndException;
import com.example.trs.exceptions.WrongTypeException;
import com.example.trs.model.WorkingTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;



public interface WorkingTimeService {

    WorkingTime startWorkingTime(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException;
    WorkingTime endWorkingTime(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotEndException;

    List<WorkingTime> getAll();
    WorkingTime getById(int id);
    List<WorkingTime> getByConsultantIdToday(int consultantId) throws JsonProcessingException, EmployeeNotFoundException;
    WorkingTime getOpenWorkingTimeTodayByConsultantId(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException;
    List<WorkingTime> getByDate(LocalDate date);
    List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date);

}
