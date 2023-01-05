package com.example.trs.service;

import com.example.trs.dto.EmployeeDTO;
import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.model.Consultant;
import com.example.trs.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAll();
    Employee getById(int id) throws EmployeeNotFoundException;
    List<Consultant> getAllConsultants();
    List<Employee> getAllNoneConsultants();
    List<Employee> getManagers();
    List<Employee> getAccountants();
    List<Employee> getTeachers();
    EmployeeDTO checkLogin(String abbr, String pass) throws EmployeeNotFoundException, JsonProcessingException;
    boolean hasRole(int employeeId, String role);



}
