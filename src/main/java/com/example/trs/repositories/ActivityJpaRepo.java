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

   // Activity findActivityByEmployeeAndCategoryAndProject();

    @Query(value = "select * from activities where employee_id= :eid and project_id= :pid and category_id= :cid" +
            " and starttime = :tstart and endtime = :tend and startdate = :dstart and enddate = :dend ", nativeQuery = true)
    Activity findActivityByEmployeeProjectCategory(@Param("eid") int employeeId, @Param("pid") int projectId, @Param("cid") int categoryId,
                                                   @Param("dstart") LocalDate startDate, @Param("tstart") LocalTime startTime,
                                                   @Param("dend") LocalDate endDate, @Param("tend") LocalTime endTime);



}
