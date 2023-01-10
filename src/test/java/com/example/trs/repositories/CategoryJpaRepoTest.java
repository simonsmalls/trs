package com.example.trs.repositories;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryJpaRepoTest {

    @Autowired
    CategoryJpaRepo categoryJpaRepo;

    @Test
    void findNameOfCategory() {
        assertEquals("Administratie", categoryJpaRepo.findById(4).orElseThrow(NullPointerException::new).getName().trim());
    }

}