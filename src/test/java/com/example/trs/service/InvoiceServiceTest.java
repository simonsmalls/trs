package com.example.trs.service;

import com.example.trs.exceptions.InvoiceNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    InvoiceService invoiceService;



    @Test
    public void createInvoiceTest() throws ProjectNotFoundException, InvoiceNotFoundException {
        invoiceService.createInvoiceForLastMonthOfProjectId(2);
    }

    @Test
    public void findInvoiceByIdTest(){
        invoiceService.finaliseInvoiceById(2);
    }

    @Test
    public void findInvoiceOfProjectIdAndMonthTest() {
        assertEquals(1, invoiceService.findInvoiceByProjectIdAndDate(1, LocalDate.of(2021,1, 1)).getId());
    }

    @Test
    public void findInvoiceOfProjectIdAndMonthReturnsNullTest() {
        assertEquals(null, invoiceService.findInvoiceByProjectIdAndDate(1, LocalDate.of(3201,1, 1)) );

    }






}


