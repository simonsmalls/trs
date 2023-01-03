package com.example.trs.mapper;

import com.example.trs.dto.CompanyDTO;
import com.example.trs.model.Company;

public class CompanyMapper {


    public static Company toCompany(CompanyDTO dto){
        Company company=new Company();
        company.setId(dto.getId());
        company.setCompanyName(dto.getName());
        return company;

    }

    public static CompanyDTO toDTO(Company company){

        CompanyDTO dto=new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getCompanyName());
        return dto;
    }
}
