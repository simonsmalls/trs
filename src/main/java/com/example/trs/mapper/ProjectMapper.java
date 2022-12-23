package com.example.trs.mapper;

import com.example.trs.dto.ProjectDTO;
import com.example.trs.model.Company;
import com.example.trs.model.Project;
import com.example.trs.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectMapper {
    @Autowired
    static
    ProjectService projectService;



    public static Project toProject(ProjectDTO dto
    ){
        Project project=new Project();
        if( dto.getId()!=0) {
            project.setClient(projectService.getCompanyById(dto.getClientId()));
        }else{
            Company c=new Company();
            c.setId(dto.getClientId());
            c.setCompanyName(dto.getClientName());
            project.setClient(c);
        }
        if( dto.getDescription()!=null) {
            project.setDescription(dto.getDescription());
        }
        project.setName(dto.getName());


        // todo
        if( dto.getHourlyRate()!=0) {
            project.setHourlyRate(dto.getHourlyRate());
        }
        project.setStartDate(dto.getStart());
        if( dto.getEnd()!=null) {
            project.setEndDate(dto.getEnd());

        }
        project.setId(dto.getId());


        return project;


    }

    public static ProjectDTO toDTO(Project project){
        ProjectDTO dto=new ProjectDTO();

        dto.setId(project.getId());
        if(project.getEndDate()!=null) {
            dto.setEnd(project.getEndDate());
        }
        dto.setStart(project.getStartDate());
        dto.setHourlyRate(project.getHourlyRate());
        dto.setClientId(project.getClient().getId());
        dto.setClientName(project.getClient().getCompanyName());
        dto.setName(project.getName());
        if(project.getDescription()!=null) {
            dto.setDescription(project.getDescription());
        }



        return dto;

    }
}