package com.example.trs.repositories;

import com.example.trs.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJpaRepo extends JpaRepository<Category, Integer> {


    Optional<Category> findCategoryByName(String name);

}
