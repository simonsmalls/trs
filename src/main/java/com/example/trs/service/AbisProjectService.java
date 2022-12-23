package com.example.trs.service;

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
        return null;
    }

    public Company getCompanyByIdAndName(int id, String name){

        return companyJpaRepo.findCompanyByIdAndCompanyName(id,name);
    }

    @Override
    public Company getCompanyById(int id) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public Project getProjectById(int id) {
        return null;
    }

    @Override
    public Project getProjectByName(String name) {
        return null;
    }

    @Override
    public List<Project> getProjectsByCompany(Company company) {
        return null;
    }

    @Override
    public void addProject(Project project) {

    }
}
