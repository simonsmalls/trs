package com.example.trs.service;

import com.example.trs.exceptions.InvoiceNotFoundException;
import com.example.trs.exceptions.ProjectNotFoundException;
import com.example.trs.model.Activity;
import com.example.trs.model.Invoice;
import com.example.trs.model.Project;
import com.example.trs.repositories.ActivityJpaRepo;
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
    public void createInvoiceForLastMonthOfProjectId(int projectId) throws ProjectNotFoundException, InvoiceNotFoundException {

        //This month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        LocalDate startDateThisMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate endDateThisMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        System.out.println("Start date this month:" + startDateThisMonth);
        System.out.println("End date this month:" + endDateThisMonth);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        //Last month
        calendar.add(Calendar.MONTH, -1);
        LocalDate startDateLastMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate endDateLastMonth = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        System.out.println("Start date last month:" + startDateLastMonth);
        System.out.println("End date last month:" + endDateLastMonth);
        //This month
        try {
            List<Activity> activityListThisMonth = activityService.findActivitiesForProjectOfMonth(projectId, startDateThisMonth, endDateThisMonth);
            double totalActivityTimeInHours = this.calculateHoursFromActivityList(activityListThisMonth);

            Invoice invoiceThisMonth = invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDateThisMonth, endDateThisMonth);
            Project projectThisMonth = invoiceThisMonth.getProject();
            invoiceThisMonth.setDate(LocalDate.now());
            invoiceThisMonth.setTotalPrice(projectThisMonth.getHourlyRate() * totalActivityTimeInHours);
            System.out.println("Id invoice this month: " + invoiceThisMonth.getId());
            System.out.println("Price invoice this month" + invoiceThisMonth.getTotalPrice());
            System.out.println("Invoice eddited");
            invoiceJpaRepo.save(invoiceThisMonth);


        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Invoice invoiceThisMonth = new Invoice();
            invoiceThisMonth.setProject(projectService.getProjectById(projectId));
            invoiceThisMonth.setDate(LocalDate.now());
            Project projectThisMonth = invoiceThisMonth.getProject();
            List<Activity> activityListThisMonth = activityService.findActivitiesForProjectOfMonth(projectId, startDateThisMonth, endDateThisMonth);
            double totalActivityTimeInHours = this.calculateHoursFromActivityList(activityListThisMonth);
            invoiceThisMonth.setTotalPrice(projectThisMonth.getHourlyRate() * totalActivityTimeInHours);
            System.out.println("New  invoice this month id: " + invoiceThisMonth.getId());
            System.out.println("New invoice this month price " + invoiceThisMonth.getTotalPrice());
            invoiceJpaRepo.save(invoiceThisMonth);
            System.out.println("New invoice created");
        }

        //Last month

        try {
            List<Activity> activityListLastMonth = activityService.findActivitiesForProjectOfMonth(projectId, startDateLastMonth, endDateLastMonth);
            double totalActivityTimeInHoursLastMonth = this.calculateHoursFromActivityList(activityListLastMonth);

            Invoice invoiceLastMonth = invoiceJpaRepo.findInvoiceByProjectIdAndStartAndEndDAndDate(projectId, startDateLastMonth, endDateLastMonth);
            Project projectLast = invoiceLastMonth.getProject();
            invoiceLastMonth.setTotalPrice(projectLast.getHourlyRate() * totalActivityTimeInHoursLastMonth);

            invoiceJpaRepo.save(invoiceLastMonth);
            System.out.println("Invoice last month saved");

        } catch (NullPointerException e) {
           // throw new InvoiceNotFoundException("Geen facturen gevonden voor vorige maand");
            System.out.println("huh");
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
    public Invoice getByCompanyId(int id) {
        return invoiceJpaRepo.findInvoiceByCompanyId(id);
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

    @Override
    public Invoice finaliseInvoiceById(int id) {
        Invoice invoice = invoiceJpaRepo.findInvoiceById(id);
        invoice.setClosed(true);
        invoice.setDate(LocalDate.now());
        return invoiceJpaRepo.save(invoice);
    }

/*    @Override
    public Invoice findInvoiceByProjectIdAndDate(int projectId, LocalDate startDate, LocalDate endDate) {
        return invoiceJpaRepo.findInvoiceByProjectIdAndDate(projectId, startDate, endDate);
    }*/

    @Override
    public Invoice findInvoiceByProjectIdAndDate(int projectId, LocalDate startDate) {
        return invoiceJpaRepo.findInvoiceByProjectIdAndDate(projectId, startDate);

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
