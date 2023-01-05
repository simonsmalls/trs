package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.model.Category;
import com.example.trs.repositories.ActivityJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AbisAnalyzeService implements AnalyzeService {
    @Autowired
    ActivityJpaRepo activityJpaRepo;

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    EmployeeService employeeService;


    @Override
    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectId(int id, LocalDate start, LocalDate end) {
        List<AnalyzeDTO> dtoList=new ArrayList<>();
       List< Object[]> list = activityJpaRepo.findActivitiesByProjectId(id,start,end);
        for ( Object[] object:list){
            AnalyzeDTO dto=new AnalyzeDTO();

            Category cat= categoryService.findCategoryByID(Integer.parseInt( object[0].toString()));
            dto.setCategory(cat.getName());
            dto.setTimeWorked(Integer.parseInt( object[1].toString()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
