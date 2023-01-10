package com.example.trs.controller;

import com.example.trs.dto.CategoryDTO;
import com.example.trs.mapper.CategoryMapper;
import com.example.trs.model.Category;
import com.example.trs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

;

@RestController
@RequestMapping(value = "category")
@CrossOrigin(origins="http://localhost:4200")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping("")
    List<CategoryDTO>   getAll()  {

        List<Category>list =  categoryService.getAll();
        return  list.stream().map(x-> CategoryMapper.toDTO(x)).collect(Collectors.toList());
    }







}
