package com.example.trs.service;

import com.example.trs.model.Invoice;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;

@Service
public class AbisInvoiceService implements InvoiceService {
    @Override
    public Invoice getByProjectId(int id) {
        return null;
    }

    @Override
    public Invoice getByCompanyId(int id) {
        return null;
    }

    @Override
    public Invoice getByMonthAndYear(Month month, Year year) {
        return null;
    }

    @Override
    public void addToPrice(double price) {

    }

    @Override
    public void addInvoice(Invoice invoice, boolean sent) {

    }
}
