package com.example.trs.service;

import com.example.trs.exceptions.CompanyAlreadyExists;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.model.Company;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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






}