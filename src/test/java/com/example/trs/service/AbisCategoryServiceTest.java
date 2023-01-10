package com.example.trs.service;

import com.example.trs.exceptions.CategoryNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AbisCategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test@Transactional
    void findCategoryByIDTest() throws CategoryNotFoundException {
        assertEquals("Les geven", categoryService.findCategoryByID(1).getName());
        assertEquals("Administratie", categoryService.findCategoryByID(2).getName());
        assertEquals("Accounting", categoryService.findCategoryByID(3).getName());
        assertEquals("Sales", categoryService.findCategoryByID(4).getName());
        assertEquals("Studeren", categoryService.findCategoryByID(5).getName());
        assertEquals("Voorbereiding eten", categoryService.findCategoryByID(6).getName());
    }

}