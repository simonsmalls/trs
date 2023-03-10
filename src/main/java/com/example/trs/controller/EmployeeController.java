package com.example.trs.controller;

;
import com.example.trs.dto.EmployeeDTO;
import com.example.trs.dto.LoginDTO;
import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.WorkingTimeCannotEndException;
import com.example.trs.exceptions.WorkingTimeCannotStartException;
import com.example.trs.exceptions.WrongTypeException;
import com.example.trs.model.Employee;
import com.example.trs.model.WorkingTime;
import com.example.trs.service.EmployeeService;
import com.example.trs.service.WorkingTimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employees")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    WorkingTimeService workingTimeService;

    @PostMapping("login")
    EmployeeDTO checkLogin(@RequestBody LoginDTO login) throws EmployeeNotFoundException, JsonProcessingException {
       return employeeService.checkLogin(login.getAbbreviation(), login.getPassword());

    }

    @GetMapping("")
    List<EmployeeDTO> getAllEmployees(){

        return employeeService.getAll();
    }

}
