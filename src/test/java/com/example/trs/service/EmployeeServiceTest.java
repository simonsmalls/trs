package com.example.trs.service;

import com.example.trs.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void findAll() {

        System.out.println(employeeService.getAll().size());

    }

    @Test
    void checkLogin() throws JsonProcessingException, EmployeeNotFoundException {

        assertEquals("JS",employeeService.checkLogin("JS", "js123").getAbbreviation());

    }

}