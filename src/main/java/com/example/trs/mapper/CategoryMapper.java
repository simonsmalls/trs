package com.example.trs.mapper;

import com.example.trs.dto.CategoryDTO;
import com.example.trs.dto.CompanyDTO;
import com.example.trs.model.Category;
import com.example.trs.model.Company;

public class CategoryMapper {


    public static Category toCompany(CategoryDTO dto){
        Category category=new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;

    }

    public static CategoryDTO toDTO(Category category){

        CategoryDTO dto=new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
