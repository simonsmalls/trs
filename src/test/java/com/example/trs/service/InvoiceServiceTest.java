package com.example.trs.service;

import com.example.trs.dto.ActivityDTO;
import com.example.trs.exceptions.*;
import com.example.trs.model.Activity;
import com.example.trs.model.Invoice;
import com.example.trs.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    ProjectService projectService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    ActivityService activityService;


    Activity activity = new Activity();
    ActivityDTO dto = new ActivityDTO();

    @BeforeEach
    void setUp() throws ProjectNotFoundException, CategoryNotFoundException {
        activity.setDescription("huh");
        activity.setProject(projectService.getProjectById(2));
        activity.setEmployee_id(1);
        activity.setCategory(categoryService.findCategoryByName("Sales"));
        activity.setStartDate(LocalDate.now());
        activity.setStartTime(LocalTime.of(14, 0));
        activity.setEndTime(LocalTime.of(15, 0));
    }



    @Test
    @Transactional
    public void calculateInvoiceTest() throws ProjectNotFoundException, InvoiceNotFoundException, ProjectAlreadyEndedException, InThePastException, ActivityTimeOverlapsException, ActivityAlreadyExistsException {
        double totalPrice = invoiceService.getByProjectId(2).get(1).getTotalPrice();
        activity.setStartDate(LocalDate.now());
        activityService.addActivity(activity);
        Project project  = invoiceService.getByProjectId(2).get(1).getProject();
        double activityPrice = (double)activity.getTimeSpent() / 60 * project.getHourlyRate();
        invoiceService.CalculateInvoices(2);
        assertEquals(totalPrice + activityPrice, invoiceService.getByProjectId(2).get(1).getTotalPrice());
    }



    @Test
    @Transactional
    public void finaliseInvoiceByIdTest(){
        Invoice invoice = new Invoice();
        invoice.setDate(LocalDate.now());
        invoice.setId(123);
        invoice.setClosed(false);
        invoiceService.createInvoice(invoice);
        assertTrue(invoiceService.finaliseInvoiceById(2).isClosed());
    }










}


