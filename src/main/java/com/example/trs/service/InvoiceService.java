package com.example.trs.service;

import com.example.trs.exceptions.InvoiceNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Invoice;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;


public interface InvoiceService {

    void createInvoiceForLastMonthOfProjectId(int projectId) throws ProjectNotFoundException, InvoiceNotFoundException;
    void createInvoice(Invoice invoice);

    List<Invoice> getByProjectId(int id);
    Invoice getByCompanyId(int id);
    Invoice getByMonthAndYear(Month month, Year year);
    void addToPrice(double price);
    void addInvoice(Invoice invoice, boolean sent);
    Invoice finaliseInvoiceById(int id);
    //Invoice findInvoiceByProjectIdAndDate(int projectId, LocalDate startDate, LocalDate endDate);
    Invoice findInvoiceByProjectIdAndDate(int projectId, LocalDate startDate);

}
