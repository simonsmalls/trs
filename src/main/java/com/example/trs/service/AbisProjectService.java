package com.example.trs.service;

import com.example.trs.model.Company;
import com.example.trs.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisProjectService implements ProjectService {
    @Override
    public List<Company> getAllCompanies() {
        return null;
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
