package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.model.Category;
import com.example.trs.repositories.ActivityJpaRepo;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public  List<AnalyzeDTO> findActivitiesByProjectIdAndDates(int id, LocalDate start, LocalDate end) {

       List< Object[]> list = activityJpaRepo.findActivitiesByProjectIdAndDates(id,start,end);
       return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByDates( LocalDate start, LocalDate end) {

        List< Object[]> list = activityJpaRepo.findActivitiesByDates(start,end);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectId(int id) {

        List< Object[]> list = activityJpaRepo.findActivitiesByProjectId(id);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeIdAndDates(int pid,int eid, LocalDate start, LocalDate end) {

        List< Object[]> list = activityJpaRepo.findActivitiesByProjectIdAndEmployeeIdAndDates(pid,eid,start,end);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByEmployee_idAndDates(int id, LocalDate start, LocalDate end) {

        List< Object[]> list = activityJpaRepo.findActivitiesByEmployee_idAndDates(id,start,end);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByEmployee_id(int id) {

        List< Object[]> list = activityJpaRepo.findActivitiesByEmployee_id(id);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeId(int project_id, int eid) {

        List< Object[]> list = activityJpaRepo.findActivitiesByProjectIdAndEmployeeId(project_id,eid);
        return toDto(list);
    }


    @Transactional
    public List<AnalyzeDTO> toDto(List<Object[]> list){
        List<AnalyzeDTO> dtoList=new ArrayList<>();
        int tot=0;
        List<Integer> integerList=new ArrayList<>();
        for ( Object[] object:list){
            AnalyzeDTO dto=new AnalyzeDTO();

            Category cat= categoryService.findCategoryByID(Integer.parseInt( object[0].toString()));
            dto.setCategory(cat.getName());
            int a=Integer.parseInt( object[1].toString());
            tot+=a;
            int b=a%60;
            int c=a/60;
            String d=b >10? ""+b:"0"+b;
            String e=c>10 ? ""+c:"0"+c;
            dto.setTimeWorked( e+":"+d  );

            dtoList.add(dto);
            integerList.add(a);
        }

        for (int i=0;i< dtoList.size();i++){

            dtoList.get(i).setPercent(Math.floor(((double)integerList.get(i)/tot)*10000)/100);

        }


        return dtoList;
    }
}
