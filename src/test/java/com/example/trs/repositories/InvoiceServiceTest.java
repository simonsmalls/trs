package com.example.trs.repositories;

import com.example.trs.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    InvoiceService invoiceService;

    @Test
    public void createInvoiceTest() {
        assertEquals(11000.00, invoiceService.createInvoiceForLastMonthOfProjectId(1).getTotalPrice());
    }

    @Test
    public void getByProjectIdTest() {
        assertEquals(20000.00, invoiceService.getByProjectId(1).getTotalPrice());
    }


}


