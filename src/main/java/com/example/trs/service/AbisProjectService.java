package com.example.trs.service;

import com.example.trs.exceptions.CompanyAlreadyExists;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Company;
import com.example.trs.model.Project;
import com.example.trs.repositories.CompanyJpaRepo;
import com.example.trs.repositories.ProjectJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisProjectService implements ProjectService {

    @Autowired
    ProjectJpaRepo projectJpaRepo;

    @Autowired
    CompanyJpaRepo companyJpaRepo;

    @Override
    public List<Company> getAllCompanies() {
        return companyJpaRepo.findAll();
    }

    public Company getCompanyByIdAndName(int id, String name){
        return null;        // TODO Why was this method added????
    }

    @Override
    public Company getCompanyById(int id) throws CompanyNotFoundException {
        return companyJpaRepo.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException("Dit bedrijf werd niet gevonden."));
    }

    @Override
    public Company getCompanyByName(String name) throws CompanyNotFoundException {
        name = name.toUpperCase();      // ignoreCase effect
        return companyJpaRepo.findCompanyByCompanyName(name)
                .orElseThrow(()-> new CompanyNotFoundException("Dit bedrijf werd niet gevonden."));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectJpaRepo.findAll();
    }

    @Override
    public Project getProjectById(int id) throws ProjectNotFoundException {
        return projectJpaRepo.findById(id).orElseThrow(()-> new ProjectNotFoundException("Dit project werd niet gevonden"));
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return null;
    }

    @Override
    public List<Project> getProjectsByCompany(Company company) {
        return null;
    }

    @Override
    public void addProject(Project project) {
        projectJpaRepo.save(project);
    }

    @Override
    public void addCompany(Company company) throws CompanyAlreadyExists {
        boolean newCompanyAdded = false;
        try {
            getCompanyByName(company.getCompanyName()); }
        catch (CompanyNotFoundException e) {
            company.setCompanyName(company.getCompanyName().toUpperCase());  // moves to Upper Case
            companyJpaRepo.save(company);
            newCompanyAdded = true;
        }
        if (!newCompanyAdded) throw new CompanyAlreadyExists("Dit bedrijf is al geregistreerd");
    }
}
