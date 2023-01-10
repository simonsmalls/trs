package com.example.trs.repositories;

import com.example.trs.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProjectJpaRepo extends JpaRepository<Project, Integer> {

    @Query(value = "select * from projects where projectName =:name", nativeQuery = true)
    List<Project> findByName(@Param("name") String projectName);

    @Query(value = "select * from projects where company_id =:id", nativeQuery = true)
    List<Project> findByClient(@Param("id") int companyId);

    Project findProjectById(int id);        // TODO why is this here?

    @Query(value = "select * from projects where projectName=:name and startdate=:startDate and enddate=:endDate " +
            "and company_id=:companyId and hourlyrate=:hourlyRate", nativeQuery = true)
    Project findProjectByNameDatesAndHourlyRate(String name, LocalDate startDate, LocalDate endDate, double hourlyRate, int companyId);        // TODO why is this here?

    @Query(value = "select * from projects where enddate >:date or enddate is null", nativeQuery = true)
    List<Project> onGoingProjects(LocalDate date);

    @Query(value = "select * from projects where enddate > CURRENT_DATE and startdate < CURRENT_DATE", nativeQuery = true)
    List<Project> findAllOngoingProjects();

}
