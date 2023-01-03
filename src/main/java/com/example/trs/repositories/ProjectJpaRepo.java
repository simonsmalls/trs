package com.example.trs.repositories;

import com.example.trs.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectJpaRepo extends JpaRepository<Project, Integer> {

    @Query(value = "select * from projects where projectName =:name", nativeQuery = true)
    List<Project> findByName(@Param("name") String projectName);

    @Query(value = "select * from projects where company_id =:id", nativeQuery = true)
    List<Project> findByClient(@Param("id") int companyId);
    Project findProjectById(int id);

}
