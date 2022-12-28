package com.example.trs.repositories;

import com.example.trs.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ActivityJpaRepo extends JpaRepository<Activity, Integer> {



    @Query(value = "select * from activities where employee_id= :id", nativeQuery = true)
    List<Activity> findActivitiesForPerson(@Param("id") int id);


    @Query(value = "select * from activities where employee_id= :eid and activities_id= :aid ", nativeQuery = true)
    Activity findActivityByEmployeeIdAndActivityId(@Param("eid") int employeeId, @Param("aid") int activityId);



}
