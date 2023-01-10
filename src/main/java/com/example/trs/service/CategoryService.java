package com.example.trs.service;

import com.example.trs.exceptions.CategoryNotFoundException;
import com.example.trs.model.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryByID(int id) throws CategoryNotFoundException;

    Category findCategoryByName(String name) throws CategoryNotFoundException;

    List<Category> getAll();
}
