package com.example.trs.service;

import com.example.trs.model.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryByID(int id);

    Category findCategoryByName(String name);

    List<Category> getAll();
}
