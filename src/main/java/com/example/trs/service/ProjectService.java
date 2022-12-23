package com.example.trs.service;

import com.example.trs.exceptions.CompanyAlreadyExists;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.model.Company;
import com.example.trs.model.Project;

import java.util.List;

public interface ProjectService {

    List<Company> getAllCompanies();
    Company getCompanyById(int id) throws CompanyNotFoundException;
    Company getCompanyByName(String name) throws CompanyNotFoundException;

    List<Project> getAllProjects();
    Project getProjectById(int id);
    Project getProjectByName(String name);
    List<Project> getProjectsByCompany(Company company);

    void addProject(Project project);
    void addCompany(Company company) throws CompanyAlreadyExists;



}
