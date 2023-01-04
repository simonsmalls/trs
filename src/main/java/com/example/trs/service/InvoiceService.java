package com.example.trs.service;

import com.example.trs.model.Invoice;

import java.time.Month;
import java.time.Year;


public interface InvoiceService {

    Invoice createInvoiceForLastMonthOfProjectId(int projectId);

    Invoice getByProjectId(int id);
    Invoice getByCompanyId(int id);
    Invoice getByMonthAndYear(Month month, Year year);
    void addToPrice(double price);
    void addInvoice(Invoice invoice, boolean sent);

}
