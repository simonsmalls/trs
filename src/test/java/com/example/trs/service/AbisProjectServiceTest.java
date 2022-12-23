package com.example.trs.service;

import com.example.trs.exceptions.CompanyAlreadyExists;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Company;
import com.example.trs.model.Project;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AbisProjectServiceTest {

    @Autowired
    AbisProjectService projectService;

    @Test
    void findCompanyByIdThrowsExceptionTest() {
        assertThrows(CompanyNotFoundException.class, ()-> projectService.getCompanyById(-5));
    }

    @Test
    void findCompanyByNameThrowsExceptionTest() {
        assertThrows(CompanyNotFoundException.class, ()-> projectService.getCompanyByName("HALLJUJAH!"));
    }

    @Test
    void findCompanyByIdTest() throws CompanyNotFoundException {
        assertEquals("ABIS", projectService.getCompanyById(1).getCompanyName().trim());
    }

    @Test
    void findCompanyByNameTest() throws CompanyNotFoundException {
        assertEquals(2, projectService.getCompanyByName("SMALS").getId());
    }

    @Test
    void findCompanyBySmallLettersTest() throws CompanyNotFoundException {
    assertEquals(2, projectService.getCompanyByName("smals").getId());
    }

    @Test
    @Transactional
    void addCompanyTest() throws CompanyAlreadyExists {
        int count = projectService.getAllCompanies().size();
        String companyName = "someWeirdIndustry";
        projectService.addCompany(new Company(companyName));
        assertEquals(count +1, projectService.getAllCompanies().size());
        assertDoesNotThrow(()-> projectService.getCompanyByName(companyName));
    }

    @Test
    @Transactional
    void addCompanyThrowsAlreadyExistsExceptionTest() {
        assertThrows(CompanyAlreadyExists.class, ()-> projectService.addCompany(new Company("smals")));
    }

    @Test
    void findProjectByIdTest() throws ProjectNotFoundException {
        assertEquals("projectnaam", projectService.getProjectById(1).getName().trim());
    }

    @Test
    @Transactional
    void addProjectTest() throws CompanyNotFoundException {
        int count = projectService.getAllProjects().size();
        projectService.addProject(new Project(projectService.getCompanyById(2), "Java", "4 month training", 100.00,
                LocalDate.of(2022, 12, 12), LocalDate.of(2023, 4, 25)));
        assertEquals(count +1, projectService.getAllProjects().size());
    }






}