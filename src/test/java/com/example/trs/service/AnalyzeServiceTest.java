package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.exceptions.CategoryNotFoundException;
import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnalyzeServiceTest {

    @Autowired
    AnalyzeService analyzeService;

    @Autowired
    EmployeeService employeeService;

    @Test@Transactional
    void findActivitiesByProjectIdAndDatesTest() throws ProjectNotFoundException, CategoryNotFoundException {
        List<AnalyzeDTO> dtos = analyzeService.findActivitiesByProjectIdAndDates(
                1, LocalDate.of(2023,1,10), LocalDate.of(2023, 8,12));
            // activities of the same category are grouped together into one dto + 1 total line added
        assertEquals(2 +1, dtos.size());     // 2 different categories + 1 total line
        assertEquals(1, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Studeren")).count());
    }

    @Test@Transactional
    void findActivitiesByDatesTest() throws ProjectNotFoundException, CategoryNotFoundException {
        assertEquals(4 +1, analyzeService.findActivitiesByDates(
                LocalDate.of(2022,12,10), LocalDate.of(2022, 12,30)).size());
    }

    @Test@Transactional
    void findActivitesByDatesShouldFindNothingTest() throws ProjectNotFoundException, CategoryNotFoundException {
        assertEquals(1, analyzeService.findActivitiesByDates(       // only the total list
                LocalDate.of(887,12,10), LocalDate.of(912, 12,30)).size());
    }

    @Test@Transactional
    void findActivitiesByProjectIdTest() throws ProjectNotFoundException, CategoryNotFoundException {
        assertEquals(4 +1, analyzeService.findActivitiesByProjectId(1).size());
    }

    @Test@Transactional
    void findActivitiesByProjectIdThrowsProjectNotFoundExceptionTest() {
        assertThrows(ProjectNotFoundException.class, ()-> analyzeService.findActivitiesByProjectId(-5));
    }

    @Test@Transactional
    void findActivitiesByProjectIdEqualsNegative1Test() throws ProjectNotFoundException, CategoryNotFoundException {
        assertEquals(1, analyzeService.findActivitiesByProjectId(-1).size());
    }       // TODO other checks need to be done?

    @Test@Transactional
    void findActivitiesByProjectIdAndEmployee_idAndDates() throws ProjectNotFoundException, CategoryNotFoundException, EmployeeNotFoundException {
        List<AnalyzeDTO> dtos = analyzeService.findActivitiesByProjectIdAndEmployeeIdAndDates(
                1,1, LocalDate.of(2023,1,10), LocalDate.of(2023, 8,12));
        assertEquals(1 +1, dtos.size());
        assertEquals(0, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Studeren")).count());
        assertEquals(1, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Les geven")).count());

        dtos = analyzeService.findActivitiesByProjectIdAndEmployeeIdAndDates(
                1,1, LocalDate.of(2023,1,10), LocalDate.of(2023, 12,30));
        assertEquals(2 +1, dtos.size());
        assertEquals(1, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Studeren")).count());
        assertEquals(1, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Les geven")).count());
        assertEquals(0, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Administratie")).count());
    }

    @Test@Transactional
    void findActivitiesByEmployee_idAndDates() throws ProjectNotFoundException, CategoryNotFoundException, EmployeeNotFoundException {
        List<AnalyzeDTO> dtos = analyzeService.findActivitiesByEmployee_idAndDates(
                1, LocalDate.of(2023,1,10), LocalDate.of(2023, 8,12));
        assertEquals(1 +1, dtos.size());
        assertEquals(0, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Studeren")).count());
        assertEquals(1, (int) dtos.stream().filter(dto -> dto.getCategory().equals("Les geven")).count());
    }

    @Test@Transactional
    void findActivitiesByEmployee_id() throws ProjectNotFoundException, CategoryNotFoundException, EmployeeNotFoundException {
        assertEquals(3 +1, analyzeService.findActivitiesByEmployee_id(2).size());
        assertEquals(2 +1, analyzeService.findActivitiesByEmployee_id(6).size());
    }

    @Test@Transactional
    void findActivitiesByEmployee_idThrowsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, ()-> analyzeService.findActivitiesByEmployee_id(-5));
        assertThrows(EmployeeNotFoundException.class, ()-> analyzeService.findActivitiesByEmployee_id(99999955));
        assertThrows(EmployeeNotFoundException.class, ()-> analyzeService.findActivitiesByEmployee_id(0));
        assertThrows(EmployeeNotFoundException.class, ()-> analyzeService.findActivitiesByEmployee_id(employeeService.getAll().size() +1));
        assertDoesNotThrow( ()-> analyzeService.findActivitiesByEmployee_id(1));
        assertDoesNotThrow( ()-> analyzeService.findActivitiesByEmployee_id(employeeService.getAll().size()));
    }

    @Test@Transactional
    void findActivitiesByProjectIdAndEmployee_id() throws ProjectNotFoundException, CategoryNotFoundException, EmployeeNotFoundException {
        assertEquals(2 +1, analyzeService.findActivitiesByProjectIdAndEmployeeId(1, 1).size());
    }





}