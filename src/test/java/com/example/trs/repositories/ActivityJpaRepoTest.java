package com.example.trs.repositories;

import com.example.trs.model.Activity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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


    @Test
    @Transactional
    void saveActivityToDatabaseTest() {
        Activity activity = new Activity();
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(90);
        Duration duration = Duration.between(startTime, endTime);
        int timeSpent = (int) duration.toMinutes();

        LocalTime testTime = LocalTime.now();
       // LocalTime timeSpent = LocalTime.ofSecondOfDay(duration.getSeconds());
        activity.setEmployee_id(9);
        activity.setTimeSpent(timeSpent);

        System.out.println(testTime);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(timeSpent);

        jpaRepo.save(activity);
        assertEquals(5, jpaRepo.findAll().size());

    }

    @Test
    void person3ShouldHave1ActivityTest() {
        assertEquals(1, jpaRepo.findActivitiesForPerson(3).size());
    }

    @Test
    void findActivityUsingEmployeeProjectCategoryTest() {
       // System.out.println(jpaRepo.findActivitiesForPerson(3).get(0).getStartActivity());
       // System.out.println(jpaRepo.findActivitiesForPerson(3).get(0).getEndActivity());
     //   System.out.println(LocalTime.now());
     //   System.out.println(jpaRepo.findActivityByEmployeeProjectCategory(9,9,9));
       assertEquals(1, jpaRepo.findActivityByEmployeeProjectCategory(3, 2, 4,
               LocalDate.of(2022,12,22), LocalTime.of(14,00,00),
               LocalTime.of(15,00,00)).getId());
    }
}