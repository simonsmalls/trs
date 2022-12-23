package com.example.trs.repositories;

import com.example.trs.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepo extends JpaRepository<Category, Integer> {


    Category findCategoryByName(String name);

}
