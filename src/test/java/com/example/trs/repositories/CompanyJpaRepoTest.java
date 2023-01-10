package com.example.trs.repositories;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyJpaRepoTest {

    @Autowired
    CompanyJpaRepo companyJpaRepo;

    @Test
    void findNumbersOfCompany() {
        assertEquals(8,  companyJpaRepo.count());
    }

    @Test
    void findNameOfCompany() {
        assertEquals("KBC", companyJpaRepo.findById(2).orElseThrow(NullPointerException::new).getCompanyName().trim());
    }

}