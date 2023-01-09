package com.example.trs.service;

import com.example.trs.dto.ConsultantSalaryDTO;
import com.example.trs.exceptions.*;
import com.example.trs.model.WorkingTime;
import com.fasterxml.jackson.core.JsonProcessingException;

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
    List<ConsultantSalaryDTO> getSalariesOfAllConsultantsFor(int year, int month) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException;
    void deleteWorkingTime(int id) throws JsonProcessingException, WorkingTimeCannotBeDeletedException;
}
