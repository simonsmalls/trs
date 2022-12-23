package com.example.trs.repositories;

import com.example.trs.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepo extends JpaRepository<Project, Integer> {


}
