package com.example.trs.service;

import com.example.trs.exceptions.EmployeeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void findAllTest() {
        assertEquals(7, employeeService.getAll().size());
    }

    /*@Test
    void checkLoginTest() throws JsonProcessingException, EmployeeNotFoundException {
        assertEquals("JS",employeeService.checkLogin("JS", "js123").getAbbreviation());
    }*/

    @Test
    void checkIfPersonExistsTest() {
        assertThrows(EmployeeNotFoundException.class, ()->  employeeService.checkIfEmployeeExists(-5));
        assertThrows(EmployeeNotFoundException.class, ()->  employeeService.checkIfEmployeeExists(99999955));
        assertThrows(EmployeeNotFoundException.class, ()->  employeeService.checkIfEmployeeExists(0));
        assertThrows(EmployeeNotFoundException.class, ()->  employeeService.checkIfEmployeeExists(employeeService.getAll().size() +1));
        assertDoesNotThrow( ()->  employeeService.checkIfEmployeeExists(1));
        assertDoesNotThrow( ()->  employeeService.checkIfEmployeeExists(employeeService.getAll().size()));
    }

}