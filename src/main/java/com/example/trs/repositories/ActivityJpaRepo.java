package com.example.trs.repositories;

import com.example.trs.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityJpaRepo extends JpaRepository<Activity, Integer> {

    @Query(value = "select * from activities where employee_id= :id", nativeQuery = true)
    List<Activity> findActivitiesForPerson(@Param("id") int id);

    @Query(value = "select * from activities where employee_id= :eid and activities_id= :aid ", nativeQuery = true)
    Activity findActivityByEmployeeIdAndActivityId(@Param("eid") int employeeId, @Param("aid") int activityId);

    Activity findActivityById(int id);

    @Query(value = "select * from activities where employee_id= :employee_id and startdate = :date order by starttime ", nativeQuery = true)
    List<Activity> findActivitiesByEmployee_idAndDate(@Param("employee_id")int employee_id,@Param("date") LocalDate date);

    @Query(value = "select * from activities where project_id= :projectId and startdate > :date ", nativeQuery = true)
    List<Activity> findActivitiesByProjectIdAfterDate(int projectId, LocalDate date);


    @Query(value = " select * from activities where project_id = :pid and startdate between :sDate and :eDate", nativeQuery = true)
    List<Activity> findActivitiesForProjectOfMonth(@Param("pid") int projectId, @Param("sDate") LocalDate startDate, @Param("eDate") LocalDate endDate);


    @Query(value = "select * from activities where employee_id= :eid and project_id= :pid and category_id= :cid " +
            " and starttime = :tstart and endtime = :tend and startdate = :dstart  ", nativeQuery = true)
    Activity findActivityByEmployeeProjectCategory(@Param("eid") int employeeId, @Param("pid") int projectId, @Param("cid") int categoryId,
                                                   @Param("dstart") LocalDate startDate, @Param("tstart") LocalTime startTime,
                                                   @Param("tend") LocalTime endTime);
    @Query(value = " select category_id,sum(timespent), project_id from activities where project_id = :pid and startdate between :sDate and :eDate group by category_id , project_id", nativeQuery = true)
    List<Object[]> findActivitiesByProjectIdAndDates(@Param("pid") int id, @Param("sDate") LocalDate start, @Param("eDate") LocalDate end);

    @Query(value = " select category_id,sum(timespent), project_id from activities where startdate between :sDate and :eDate group by category_id , project_id", nativeQuery = true)
    List<Object[]> findActivitiesByDates( @Param("sDate") LocalDate start, @Param("eDate") LocalDate end);

    @Query(value = "select sum(timespent) from activities where project_id =:pid and startDate between :sDate and :eDate", nativeQuery = true)
    Integer findSumOfTimeOfActivitiesForProject(@Param("pid") int projectId, @Param("sDate") LocalDate startDate, @Param("eDate") LocalDate endDate);
    @Query(value = " select category_id,sum(timespent) from activities where project_id = :pid group by category_id", nativeQuery = true)
    List<Object[]> findActivitiesByProjectId(@Param("pid") int id);

    @Query(value = " select category_id,sum(timespent), project_id from activities where project_id = :pid and employee_id = :eid  and startdate between :sDate and :eDate group by category_id , project_id", nativeQuery = true)
    List<Object[]> findActivitiesByProjectIdAndEmployeeIdAndDates(@Param("pid") int pid,@Param("eid") int eid, @Param("sDate") LocalDate start, @Param("eDate") LocalDate end);

    @Query(value = " select category_id,sum(timespent), project_id from activities where employee_id = :eid and startdate between :sDate and :eDate group by category_id , project_id", nativeQuery = true)
    List<Object[]> findActivitiesByEmployee_idAndDates(@Param("eid") int eid, @Param("sDate") LocalDate start, @Param("eDate") LocalDate end);

    @Query(value = " select category_id,sum(timespent), project_id from activities where employee_id = :eid group by category_id , project_id", nativeQuery = true)
    List<Object[]> findActivitiesByEmployee_id(@Param("eid") int eid);

    @Query(value = " select category_id,sum(timespent), project_id from activities where project_id = :pid and employee_id = :eid  group by category_id, project_id", nativeQuery = true)
    List<Object[]> findActivitiesByProjectIdAndEmployeeId(@Param("pid") int pid,@Param("eid") int eid);

}
