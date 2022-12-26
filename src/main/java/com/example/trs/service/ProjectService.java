package com.example.trs.service;

import com.example.trs.dto.InvoiceDTO;
import com.example.trs.dto.ProjectDTO;
import com.example.trs.exceptions.CompanyAlreadyExists;
import com.example.trs.exceptions.CompanyNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Company;
import com.example.trs.model.Invoice;
import com.example.trs.model.Project;

import java.util.List;

public interface ProjectService {

    List<Company> getAllCompanies();
    Company getCompanyById(int id) throws CompanyNotFoundException;
    Company getCompanyByName(String name) throws CompanyNotFoundException;
    Company getCompanyByIdAndName(int id, String name);
    Project toProject(ProjectDTO dto) throws CompanyNotFoundException;
    Invoice toInvoice(InvoiceDTO dto) throws ProjectNotFoundException;

    List<Project> getAllProjects();
    Project getProjectById(int id) throws ProjectNotFoundException;
    List<Project> getProjectsByName(String name) throws ProjectNotFoundException;
    List<Project> getProjectsByCompany(Company company) throws ProjectNotFoundException, CompanyNotFoundException;

    void addProject(Project project);


    void addCompany(Company company) throws CompanyAlreadyExists;
}
