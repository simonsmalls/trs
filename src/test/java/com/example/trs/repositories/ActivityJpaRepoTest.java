package com.example.trs.repositories;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivityJpaRepoTest {

    @Autowired
    ActivityJpaRepo jpaRepo;

    @Test
    void checkNumber() {
        assertEquals(3,jpaRepo.findAll().get(0).getEmployee_id());
    }

}