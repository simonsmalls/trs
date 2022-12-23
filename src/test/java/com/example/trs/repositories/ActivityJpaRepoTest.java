package com.example.trs.repositories;

import com.example.trs.model.Activity;
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

    @Test
    void findDataOfActivity() {
        Activity activity = jpaRepo.findById(1).orElseThrow(NullPointerException::new);
        assertEquals("uh", activity.getDescription().trim());
        assertEquals(3, activity.getEmployee_id());
        assertEquals(2, activity.getProject().getId());
        assertEquals(4, activity.getCategory().getId());
    }

}