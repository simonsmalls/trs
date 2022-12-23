package com.example.trs.mapper;

import com.example.trs.dto.InvoiceDTO;
import com.example.trs.model.Invoice;
import com.example.trs.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceMapper {
    @Autowired
            static
    ProjectService projectService;

    public static Invoice toInvoice(InvoiceDTO dto){

        Invoice invoice=new Invoice();

        invoice.setClosed(dto.isClosed());
        invoice.setTotalPrice(dto.getTotalPrice());
        invoice.setId(dto.getId());
        invoice.setDate(dto.getDate());
        invoice.setProject(projectService.getProjectById(dto.getProjectId()));


        return invoice;

    }


    public static InvoiceDTO toDTO(Invoice invoice){

        InvoiceDTO dto=new InvoiceDTO();

        dto.setId(invoice.getId());
        dto.setClientId(invoice.getProject().getClient().getId());
        dto.setClientName(invoice.getProject().getClient().getCompanyName());
        dto.setClosed(invoice.isClosed());
        dto.setDate(invoice.getDate());
        dto.setTotalPrice(invoice.getTotalPrice());
        dto.setProjectId(invoice.getProject().getId());
        dto.setProjectName(invoice.getProject().getName());
        return dto;

    }

}
