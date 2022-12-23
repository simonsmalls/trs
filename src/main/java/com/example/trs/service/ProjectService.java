package com.example.trs.service;

import com.example.trs.dto.InvoiceDTO;
import com.example.trs.dto.ProjectDTO;
import com.example.trs.model.Company;
import com.example.trs.model.Invoice;
import com.example.trs.model.Project;

import java.util.List;

public interface ProjectService {

    List<Company> getAllCompanies();
    Company getCompanyById(int id);

    List<Project> getAllProjects();
    Project getProjectById(int id);
    Project getProjectByName(String name);
    List<Project> getProjectsByCompany(Company company);
    Company getCompanyByIdAndName(int id, String name);
    Project toProject(ProjectDTO dto);
    Invoice toInvoice(InvoiceDTO dto);

    void addProject(Project project);



}
