package com.example.trs.service;

import com.example.trs.model.Invoice;

import java.time.Month;
import java.time.Year;
import java.util.List;


public interface InvoiceService {

    Invoice createInvoiceForLastMonthOfProjectId(int projectId);

    List<Invoice> getByProjectId(int id);
    Invoice getByCompanyId(int id);
    Invoice getByMonthAndYear(Month month, Year year);
    void addToPrice(double price);
    void addInvoice(Invoice invoice, boolean sent);
    Invoice finaliseInvoiceById(int id);

}
