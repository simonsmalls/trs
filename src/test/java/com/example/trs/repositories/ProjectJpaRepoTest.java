package com.example.trs.repositories;

import com.example.trs.model.Project;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectJpaRepoTest {

    @Autowired
    ProjectJpaRepo projectJpaRepo;

    @Test
    void findProjectByIdTest() {
        Project project = projectJpaRepo.findById(2).orElseThrow(NullPointerException::new);
        assertEquals(1, project.getClient().getId());
        assertEquals("abisproject", project.getName().trim());
        assertEquals("abisprojectomschrijving", project.getDescription().trim());
        assertEquals(1000.00, project.getHourlyRate());
        assertEquals(LocalDate.of(2022, Month.SEPTEMBER, 14), project.getStartDate());
        assertEquals(LocalDate.of(2023, 1, 22), project.getEndDate());
    }

    @Test
    void findProjectsByName() {
        assertEquals(1, projectJpaRepo.findByName("projectnaam").stream().findFirst()
                .orElseThrow(NullPointerException::new).getId());
    }


}