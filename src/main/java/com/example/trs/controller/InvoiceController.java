package com.example.trs.controller;

import com.example.trs.dto.InvoiceDTO;
import com.example.trs.exceptions.InvoiceNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.mapper.InvoiceMapper;
import com.example.trs.model.Invoice;
import com.example.trs.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "invoice")
@CrossOrigin(origins="http://localhost:4200")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;


    @GetMapping("/calculate/{id}")
    void calculateProjectInvoice(@PathVariable("id") int projectId) throws ProjectNotFoundException, InvoiceNotFoundException {
          invoiceService.CalculateInvoices(projectId);
    }

    @GetMapping("/{id}")
    List<InvoiceDTO> invoicesByProjectId(@PathVariable("id") int id) {
        List<Invoice> invoiceList = invoiceService.getByProjectId(id);
        return invoiceList.stream().map(InvoiceMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/finalise/{id}")
    void finaliseInvoice(@PathVariable("id") int id) {
        invoiceService.finaliseInvoiceById(id);
    }
}
