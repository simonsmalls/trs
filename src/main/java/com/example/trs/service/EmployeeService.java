package com.example.trs.service;

import com.example.trs.exception.EmployeeNotFoundException;
import com.example.trs.model.Consultant;
import com.example.trs.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getById(int id) throws EmployeeNotFoundException;
    List<Consultant> getAllConsultants();
    List<Employee> getAllNoneConsultants();
    List<Employee> getManagers();
    List<Employee> getAccountants();
    List<Employee> getTeachers();
    boolean checkLogin(String abbreviation, String password);
    boolean hasRole(int employeeId, String role);



}
