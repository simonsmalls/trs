package com.example.trs.service;

import com.example.trs.model.Category;

public interface CategoryService {

    Category findCategoryByID(int id);

    Category findCategoryByName(String name);
}
