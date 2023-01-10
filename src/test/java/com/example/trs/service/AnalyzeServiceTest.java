package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.exceptions.CategoryNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnalyzeServiceTest {

    @Autowired
    AnalyzeService analyzeService;

    @Test
    void findAll() throws ProjectNotFoundException, CategoryNotFoundException {

        List<AnalyzeDTO> list= analyzeService.findActivitiesByProjectIdAndDates(2, LocalDate.of(2015,8,20),LocalDate.of(2024,8,20));
        System.out.println(list.size());
    }

    @Test
    void findprojectNUll() throws ProjectNotFoundException, CategoryNotFoundException {
        analyzeService.findActivitiesByProjectId(0);
    }



}