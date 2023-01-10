package com.example.trs.service;

import com.example.trs.exceptions.InvoiceNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Invoice;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;


public interface InvoiceService {

    void CalculateInvoices(int projectId) throws ProjectNotFoundException, InvoiceNotFoundException;
    void createInvoice(Invoice invoice);
    List<Invoice> getByProjectId(int id);

    Invoice finaliseInvoiceById(int id);

    Invoice findInvoiceByProjectIdAndStartAndEndDate(int projectId, LocalDate startDate, LocalDate endDate);
}
