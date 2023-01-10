package com.example.trs.service;

import com.example.trs.dto.InvoiceDTO;
import com.example.trs.dto.ProjectDTO;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.exceptions.ProjectEndDateNotValid;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.exceptions.*;
import com.example.trs.mapper.InvoiceMapper;
import com.example.trs.mapper.ProjectMapper;
import com.example.trs.model.Activity;
import com.example.trs.model.Company;
import com.example.trs.model.Invoice;
import com.example.trs.model.Project;
import com.example.trs.repositories.CompanyJpaRepo;
import com.example.trs.repositories.ProjectJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbisProjectService implements ProjectService {

    @Autowired
    ProjectJpaRepo projectJpaRepo;

    @Autowired
    CompanyJpaRepo companyJpaRepo;
    @Autowired ActivityService activityService;


    @Override
    public List<Company> getAllCompanies() {
        return companyJpaRepo.findAll();
    }

    public Company getCompanyByIdAndName(int id, String name){
        return companyJpaRepo.findCompanyByIdAndCompanyName(id, name);
    }       // TODO Why was this method added????


    @Override
    public Company getCompanyById(int id) throws CompanyNotFoundException {
        return companyJpaRepo.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException("Dit bedrijf werd niet gevonden."));
    }

    public Invoice toInvoice(InvoiceDTO dto) throws ProjectNotFoundException {
     return InvoiceMapper.toInvoice(dto, getProjectById(dto.getProjectId()));
    }

    public Project toProject(ProjectDTO dto) throws CompanyNotFoundException {
        return ProjectMapper.toProject(dto, getCompanyById(dto.getClientId()));
    }

    @Override
    public Company getCompanyByName(String name) throws CompanyNotFoundException {
        name = name.toUpperCase();      // ignoreCase effect
        return companyJpaRepo.findCompanyByCompanyName(name)
                .orElseThrow(()-> new CompanyNotFoundException("Er werd geen bedrijf gevonden met deze naam."));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectJpaRepo.findAll();
    }

    @Override
    public Project getProjectById(int id) throws ProjectNotFoundException {
        if(id==0) return null;
        return projectJpaRepo.findById(id).orElseThrow(()-> new ProjectNotFoundException("Dit project werd niet gevonden."));
    }

    @Override
    public List<Project> getProjectsByName(String name) throws ProjectNotFoundException {
        List<Project> projects = projectJpaRepo.findByName(name);
        if (projects.size() == 0) throw new ProjectNotFoundException("Er werden geen projecten gevonden met deze naam.");
        return projects;
    }

    @Override
    public List<Project> getProjectsByCompany(Company company) throws ProjectNotFoundException, CompanyNotFoundException {
        getCompanyByName(company.getCompanyName()); // checks if company is already registered
        List<Project> projects = projectJpaRepo.findByClient(company.getId());
        if (projects.size() == 0) throw new ProjectNotFoundException("Er werden geen projecten gevonden voor dit bedrijf.");
        return projects;
    }

    //TODO check if project already exists
    @Override
    public void addProject(Project project) throws WrongTimeException, InThePastException, ProjectAlreadyExistsException {
        if (project.getEndDate().isBefore(project.getStartDate())) throw new WrongTimeException("Einddatum moet na startdatum komen");
        if (project.getStartDate().isBefore(LocalDate.now())) throw new InThePastException("Nieuw project kan niet in het verleden beginnen");


        Project existing = projectJpaRepo.findProjectByNameDatesAndHourlyRate(project.getName(), project.getStartDate(), project.getEndDate(),
                    project.getHourlyRate(), project.getClient().getId());
        if (existing != null) throw new ProjectAlreadyExistsException("Project bestaat al");

        projectJpaRepo.save(project);
    }

    // update project with a new endDate
    @Override
    public Project setEndDate(int projectId, LocalDate endDate) throws ProjectNotFoundException, ProjectEndDateNotValid {
        Project p = getProjectById(projectId);

        // check if there are activities for this project after the end Date
        List<Activity> activities = activityService.findActivitiesByProjectAfterDate(projectId, endDate);
        if (activities.size() != 0) throw new ProjectEndDateNotValid("dit project heeft na deze einddatum nog activiteiten");
        p.setEndDate(endDate);
        return projectJpaRepo.save(p);
    }

    @Override
    public List<Project> ongoingProjects() {
        return projectJpaRepo.onGoingProjects(LocalDate.now());
    }

    @Override
    public void addCompany(Company company) throws CompanyAlreadyExistsException {
        boolean newCompanyAdded = false;
        boolean idDoesNotExist = false;
        boolean nameDoesNotExist = false;
        try { getCompanyById(company.getId()); } catch (CompanyNotFoundException e) { idDoesNotExist = true; }
        try { getCompanyByName(company.getCompanyName()); } catch (CompanyNotFoundException e) { nameDoesNotExist = true; }

        if (idDoesNotExist && nameDoesNotExist) {
            company.setCompanyName(company.getCompanyName().toUpperCase());  // moves to Upper Case
            companyJpaRepo.save(company);
            newCompanyAdded = true;
        }
        if (!newCompanyAdded) throw new CompanyAlreadyExistsException("Dit bedrijf is al geregistreerd");
    }

    @Override
    public List<Project> getOngoingProjects() {
        return projectJpaRepo.findAllOngoingProjects();
    }
}
