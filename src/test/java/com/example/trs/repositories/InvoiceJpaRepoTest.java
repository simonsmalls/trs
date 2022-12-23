package com.example.trs.repositories;

import com.example.trs.model.Invoice;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InvoiceJpaRepoTest {

    @Autowired
    InvoiceJpaRepo invoiceJpaRepo;

    @Test
    void findDataOfInvoice() {
        Invoice invoice = invoiceJpaRepo.findById(1).orElseThrow(NullPointerException::new);
        assertEquals(20000.00, invoice.getTotalPrice());
        assertEquals(1, invoice.getProject().getId());
        assertEquals(LocalDate.of(2021, Month.JANUARY, 28), invoice.getDate());
    }



}