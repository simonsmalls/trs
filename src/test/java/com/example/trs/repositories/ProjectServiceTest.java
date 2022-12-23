package com.example.trs.repositories;

import com.example.trs.model.Project;
import com.example.trs.service.ProjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    void findByNameAndId() {

        System.out.println(projectService.getCompanyByIdAndName(1,"ABIS").getCompanyName());

    }

}