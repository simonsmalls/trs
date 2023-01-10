package com.example.trs.service;

import com.example.trs.dto.EmployeeDTO;
import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.model.Consultant;
import com.example.trs.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAll();

    void checkIfEmployeeExists(int id) throws EmployeeNotFoundException;

    EmployeeDTO checkLogin(String abbr, String pass) throws EmployeeNotFoundException, JsonProcessingException;
    boolean hasRole(int employeeId, String role);



}
