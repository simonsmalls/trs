package com.example.trs.service;

import com.example.trs.exceptions.InvoiceNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Activity;
import com.example.trs.model.Invoice;
import com.example.trs.model.Project;
import com.example.trs.repositories.InvoiceJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;


@Service
public class AbisInvoiceService implements InvoiceService {

    @Autowired
    ActivityService activityService;

    @Autowired
    InvoiceJpaRepo invoiceJpaRepo;

    @Autowired
    ProjectService projectService;


    @Override
    public void CalculateInvoices(int projectId) throws ProjectNotFoundException{

        //This month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        LocalDate startDateThisMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate endDateThisMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //Last month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH, -1);
        LocalDate startDateLastMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate endDateLastMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //next month
        calendar.add(Calendar.MONTH, 2);
        LocalDate endDateNextMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        LocalDate startDateNextMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //month after that
        calendar.add(Calendar.MONTH, 1);
        LocalDate startDateFinalMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate endDateFinalMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        //This month

        if (invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDateThisMonth, endDateThisMonth) != null) {
            InvoiceForMonth(projectId, startDateThisMonth, endDateThisMonth);
        } else if (invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDateThisMonth, endDateThisMonth) == null) {
            Invoice invoiceThisMonth = new Invoice();
            invoiceThisMonth.setProject(projectService.getProjectById(projectId));
            invoiceThisMonth.setDate(endDateThisMonth);
            Project projectThisMonth = invoiceThisMonth.getProject();
            List<Activity> activityListThisMonth = activityService.findActivitiesForProjectOfMonth(projectId, startDateThisMonth, endDateThisMonth);
            double totalActivityTimeInHours = this.calculateHoursFromActivityList(activityListThisMonth);
            invoiceThisMonth.setTotalPrice(projectThisMonth.getHourlyRate() * totalActivityTimeInHours);
            invoiceJpaRepo.save(invoiceThisMonth);

        }

        //Last month
        InvoiceForMonth(projectId, startDateLastMonth, endDateLastMonth);

        //Next month
        InvoiceForMonth(projectId, startDateNextMonth, endDateNextMonth);

        //Month after that
        InvoiceForMonth(projectId, startDateFinalMonth, endDateFinalMonth);


    }

    private void InvoiceForMonth(int projectId, LocalDate startDateFinalMonth, LocalDate endDateFinalMonth) {
        if (invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDateFinalMonth, endDateFinalMonth) != null) {
            Invoice invoiceFinalMonth = invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDateFinalMonth, endDateFinalMonth);
            List<Activity> activityListFinalMonth = activityService.findActivitiesForProjectOfMonth(projectId, startDateFinalMonth, endDateFinalMonth);
            double totalActivityTimeInHoursFinalMonth = this.calculateHoursFromActivityList(activityListFinalMonth);
            Project projectFinal = invoiceFinalMonth.getProject();
            invoiceFinalMonth.setTotalPrice(projectFinal.getHourlyRate() * totalActivityTimeInHoursFinalMonth);
            invoiceJpaRepo.save(invoiceFinalMonth);
        }
    }

    @Override
    public void createInvoice(Invoice invoice) {
        invoiceJpaRepo.save(invoice);
    }

    @Override
    public List<Invoice> getByProjectId(int id) {

        return invoiceJpaRepo.findAllByProjectId(id);
    }

    @Override
    public Invoice finaliseInvoiceById(int id) {
        Invoice invoice = invoiceJpaRepo.findInvoiceById(id);
        invoice.setClosed(true);
        return invoiceJpaRepo.save(invoice);
    }

    @Override
    public Invoice findInvoiceByProjectIdAndStartAndEndDate(int projectId, LocalDate startDate, LocalDate endDate) {
        return invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDate, endDate);
    }


    double calculateHoursFromActivityList(List<Activity> activityList) {
        int totalActivityTime = 0;
        for (Activity activity : activityList) {
            totalActivityTime = totalActivityTime + activity.getTimeSpent();
        }
        int remainder = totalActivityTime % 15;
        if (remainder == 0) {
            remainder = 15;
        }
        return (double) (totalActivityTime + (15 - remainder)) / 60;
    }
}
