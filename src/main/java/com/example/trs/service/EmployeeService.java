package com.example.trs.service;

import com.example.trs.dto.LoginDTO;
import com.example.trs.exception.EmployeeNotFoundException;
import com.example.trs.model.Consultant;
import com.example.trs.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getById(int id) throws EmployeeNotFoundException;
    List<Consultant> getAllConsultants();
    List<Employee> getAllNoneConsultants();
    List<Employee> getManagers();
    List<Employee> getAccountants();
    List<Employee> getTeachers();
    Employee checkLogin(String abbr,String pass) throws EmployeeNotFoundException, JsonProcessingException;
    boolean hasRole(int employeeId, String role);



}
