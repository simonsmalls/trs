package com.example.trs.service;

import com.example.trs.exceptions.CategoryNotFoundException;
import com.example.trs.model.Category;
import com.example.trs.repositories.CategoryJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisCategoryService implements CategoryService{

    @Autowired
    CategoryJpaRepo categoryJpaRepo;

    @Override
    public Category findCategoryByID(int id) throws CategoryNotFoundException {
        return categoryJpaRepo.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Er werd geen categorie gevonden met deze id."));
    }

    @Override
    public Category findCategoryByName(String name) throws CategoryNotFoundException {
        return categoryJpaRepo.findCategoryByName(name)
                .orElseThrow(()-> new CategoryNotFoundException("Er werd geen categorie gevonden met deze naam."));
    }

    @Override
    public List<Category> getAll() {
        return categoryJpaRepo.findAll();
    }
}
