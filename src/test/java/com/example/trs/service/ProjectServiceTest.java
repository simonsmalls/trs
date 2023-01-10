package com.example.trs.service;

import com.example.trs.exceptions.*;
import com.example.trs.model.Company;
import com.example.trs.model.Project;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectServiceTest {

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
    void addCompanyTest() throws CompanyAlreadyExistsException {
        int count = projectService.getAllCompanies().size();
        String companyName = "someWeirdIndustry";
        projectService.addCompany(new Company(companyName));
        assertEquals(count +1, projectService.getAllCompanies().size());
        assertDoesNotThrow(()-> projectService.getCompanyByName(companyName));
    }

    @Test
    @Transactional
    void addCompanyThrowsAlreadyExistsExceptionTest() {
        assertThrows(CompanyAlreadyExistsException.class, ()-> projectService.addCompany(new Company("smals")));
    }

    @Test
    void findProjectByIdTest() throws ProjectNotFoundException {
        assertEquals("projectnaam", projectService.getProjectById(1).getName().trim());
    }

    @Test
    void findProjectByByNameTest() throws ProjectNotFoundException {
        assertEquals(1, projectService.getProjectsByName("projectnaam").stream().findFirst()
                .orElseThrow(NullPointerException::new).getId());
    }

    @Test
    void findProjectByNameThrowsExceptionTest() {
        assertThrows(ProjectNotFoundException.class, ()-> projectService.getProjectsByName("lalaLand.com"));
    }

    @Test
    void findProjectsByCompanyTest() throws CompanyNotFoundException, ProjectNotFoundException {
        assertEquals(2, projectService.getProjectsByCompany(projectService.getCompanyById(1)).stream().findFirst()
                .orElseThrow(NullPointerException::new).getId());
    }

    @Test
    void findProjectByCompanyThrowsNoCompanyFoundExceptionTest() {
        Company company = new Company("Unexisting_Company");
        assertThrows(CompanyNotFoundException.class, () -> projectService.getProjectsByCompany(company));
    }

    @Test
    @Transactional
    void findProjectByCompanyThrowsNoProjectsFoundException() throws CompanyAlreadyExistsException {
        Company company = new Company("Newly_Created_Company");
        projectService.addCompany(company);
        assertThrows(ProjectNotFoundException.class, ()-> projectService.getProjectsByCompany(company));
    }



    @Test
    @Transactional
    void addProjectTest() throws CompanyNotFoundException, ProjectAlreadyExistsException, WrongTimeException, InThePastException {
        int count = projectService.getAllProjects().size();
        projectService.addProject(new Project(projectService.getCompanyById(2), "Java", "4 month training", 100.00,
                LocalDate.of(2022, 12, 12), LocalDate.of(2023, 4, 25)));
        assertEquals(count +1, projectService.getAllProjects().size());
    }

    @Test
    void timetest(){
        int a=120;
        int b=a%60;
        int c=a/60;
        System.out.println(LocalTime.of(c,b));
    }


    @Test
    @Transactional
    void setEndDateOnProjectTest() throws ProjectNotFoundException, ProjectEndDateNotValid {
        assertEquals(LocalDate.of(2023,12,27), projectService.setEndDate(1, LocalDate.of(2023,12,27)).getEndDate());
    }

    @Test
    @Transactional
    void setEndDateOnProjectTestThrowsException() throws ProjectNotFoundException, ProjectEndDateNotValid {
        assertThrows(ProjectEndDateNotValid.class, ()-> projectService.setEndDate(1, LocalDate.of(2023,11,27)));
    }


    // Test from a secondary ProjectServiceTest Class moved to this place, in order to end up with one ProjectServiceTest Class

    @Test
    void findByNameAndId() {
        System.out.println(projectService.getCompanyByIdAndName(1,"ABIS").getCompanyName());
    }

    @Test
    void getAllTest() {
        System.out.println(projectService.getAllProjects().size());
    }

}