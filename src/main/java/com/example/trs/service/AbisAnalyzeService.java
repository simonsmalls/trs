package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.exceptions.ProjectNotFoundException;
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
import java.util.stream.Collectors;

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
    public  List<AnalyzeDTO> findActivitiesByProjectIdAndDates(int id, LocalDate start, LocalDate end) throws ProjectNotFoundException {

       List< Object[]> list = activityJpaRepo.findActivitiesByProjectIdAndDates(id,start,end);
       return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByDates( LocalDate start, LocalDate end) throws ProjectNotFoundException {

        List< Object[]> list = activityJpaRepo.findActivitiesByDates(start,end);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectId(int id) throws ProjectNotFoundException {
        List<Object[]> list;
        if(id!=-1) {
             list = activityJpaRepo.findActivitiesByProjectId(id);
        }else{
             list = activityJpaRepo.findActivitiesByProjectId0();
        }
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeIdAndDates(int pid,int eid, LocalDate start, LocalDate end) throws ProjectNotFoundException {

        List< Object[]> list ;

        if(pid!=-1) {
            list = activityJpaRepo.findActivitiesByProjectIdAndEmployeeIdAndDates(pid,eid,start,end);
        }else{
            list = activityJpaRepo.findActivitiesByProjectId0AndEmployeeIdAndDates(eid,start,end);
        }
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByEmployee_idAndDates(int id, LocalDate start, LocalDate end) throws ProjectNotFoundException {

        List< Object[]> list = activityJpaRepo.findActivitiesByEmployee_idAndDates(id,start,end);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByEmployee_id(int id) throws ProjectNotFoundException {

        List< Object[]> list = activityJpaRepo.findActivitiesByEmployee_id(id);
        return toDto(list);
    }

    @Transactional
    public  List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeId(int project_id, int eid) throws ProjectNotFoundException {

        List< Object[]> list = activityJpaRepo.findActivitiesByProjectIdAndEmployeeId(project_id,eid);

        if(project_id!=-1) {
            list = activityJpaRepo.findActivitiesByProjectIdAndEmployeeId(project_id,eid);
        }else{
            list = activityJpaRepo.findActivitiesByProjectId0AndEmployeeId(eid);
        }
        return toDto(list);
    }


    @Transactional
    public List<AnalyzeDTO> toDto(List<Object[]> list) throws ProjectNotFoundException {
        List<AnalyzeDTO> dtoList=new ArrayList<>();
        int tot=0;

        for ( Object[] object:list){


            Category cat= categoryService.findCategoryByID(Integer.parseInt( object[0].toString()));
            if(dtoList.stream().map(x->x.getCategory()).collect(Collectors.toList()).contains(cat.getName())) {
                AnalyzeDTO dto2= dtoList.stream().filter(x-> x.getCategory()==cat.getName()).findFirst().get();
                int a = Integer.parseInt(object[1].toString());
                tot += a;
                dto2.setTimeWorked(dto2.getTimeWorked()+a);
                if (object[2]!=null) {
                    int projectid = Integer.parseInt(object[2].toString());
                    if (projectid != 0) {

                        double hourlyrate = projectService.getProjectById(projectid).getHourlyRate();
                        int b = a / 60;
                        int c = a % 60;
                        dto2.setMoney(dto2.getMoney() + b * hourlyrate + (c / 60) * hourlyrate);

                    }
                }

            }else {
                AnalyzeDTO dto = new AnalyzeDTO();
                dto.setCategory(cat.getName());
                int a = Integer.parseInt(object[1].toString());
                tot += a;

                dto.setTimeWorked(a);
                if (object[2]!=null) {
                    int projectid = Integer.parseInt(object[2].toString());
                    if (projectid != 0) {
                        double hourlyrate = projectService.getProjectById(projectid).getHourlyRate();
                        int b = a / 60;
                        int c = a % 60;
                        dto.setMoney(b * hourlyrate + (c / 60) * hourlyrate);

                    } else {
                        dto.setMoney(0);
                    }
                }else {
                    dto.setMoney(0);
                }

                dtoList.add(dto);
            }
        }

        for (int i=0;i< dtoList.size();i++){
            dtoList.get(i).setPercent(Math.floor(((double)dtoList.get(i).getTimeWorked()/tot)*10000)/100);

        }
        AnalyzeDTO total=new AnalyzeDTO();
        total.setCategory("total");
        total.setPercent(100);
        total.setTimeWorked(dtoList.stream()
                        .map(x->x.getTimeWorked())
                        .mapToInt(Integer::intValue).sum()
                );
        total.setMoney(dtoList.stream()
                .map(x->x.getMoney())
                .mapToDouble(Double::doubleValue).sum()
        );
        dtoList.add(total);

        return dtoList;
    }
}
