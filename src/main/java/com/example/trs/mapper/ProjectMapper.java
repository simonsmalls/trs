package com.example.trs.mapper;

import com.example.trs.dto.ProjectDTO;
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
        if( dto.getClientName()!=null) {
            project.setClient(projectService.getCompanyByIdAndName(dto.getClientId(), dto.getClientName()));
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

        dto.setClientId(project.getId());
        if(project.getEndDate()!=null) {
            dto.setEnd(project.getEndDate());
        }
        dto.setStart(project.getStartDate());


        return dto;

    }
}