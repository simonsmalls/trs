package com.example.trs.service;

import com.example.trs.model.Category;
import com.example.trs.repositories.CategoryJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbisCategoryService implements CategoryService{

    @Autowired
    CategoryJpaRepo categoryJpaRepo;

    @Override
    public Category findCategoryByID(int id) {
        return categoryJpaRepo.getById(id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryJpaRepo.findCategoryByName(name);
    }
}
