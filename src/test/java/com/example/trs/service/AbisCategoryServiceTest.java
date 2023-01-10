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
    void findCategoryByIdTest() throws CategoryNotFoundException {
        assertEquals("Les geven", categoryService.findCategoryByID(1).getName());
        assertEquals("Administratie", categoryService.findCategoryByID(2).getName());
        assertEquals("Accounting", categoryService.findCategoryByID(3).getName());
        assertEquals("Sales", categoryService.findCategoryByID(4).getName());
        assertEquals("Studeren", categoryService.findCategoryByID(5).getName());
        assertEquals("Voorbereiding eten", categoryService.findCategoryByID(6).getName());
    }

    @Test@Transactional
    void findCategoryByNameTest() throws CategoryNotFoundException {
        assertEquals(1, categoryService.findCategoryByName("Les geven").getId());
        assertEquals(2, categoryService.findCategoryByName("Administratie").getId());
        assertEquals(3, categoryService.findCategoryByName("Accounting").getId());
        assertEquals(4, categoryService.findCategoryByName("Sales").getId());
        assertEquals(5, categoryService.findCategoryByName("Studeren").getId());
        assertEquals(6, categoryService.findCategoryByName("Voorbereiding eten").getId());
    }

    @Test@Transactional
    void findCategoryByIdThrowsNotFoundExceptionTest() {
        assertThrows(CategoryNotFoundException.class, ()-> categoryService.findCategoryByID(-5));
    }

    @Test@Transactional
    void findCategoryByNameThrowsNotFoundExceptionTest() {
        assertThrows(CategoryNotFoundException.class, ()-> categoryService.findCategoryByName("Hocus Pocus Pas"));
    }

    @Test@Transactional
    void getAllCategoriesTest() {
        assertEquals("Les geven", categoryService.getAll().get(0).getName());
        assertEquals("Administratie", categoryService.getAll().get(1).getName());
        assertEquals("Accounting", categoryService.getAll().get(2).getName());
        assertEquals("Sales", categoryService.getAll().get(3).getName());
        assertEquals("Studeren", categoryService.getAll().get(4).getName());
        assertEquals("Voorbereiding eten", categoryService.getAll().get(5).getName());
        assertEquals(6, categoryService.getAll().size());
    }

}