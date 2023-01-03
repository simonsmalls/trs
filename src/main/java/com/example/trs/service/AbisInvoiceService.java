package com.example.trs.service;

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
import java.util.stream.Collectors;


@Service
public class AbisInvoiceService implements InvoiceService {

    @Autowired
    ActivityService activityService;

    @Autowired
    InvoiceJpaRepo invoiceJpaRepo;


    @Override
    public Invoice createInvoiceForLastMonthOfProjectId(int projectId) {

        //Set calendar to first day of month and subtract 1 month, covert to Localdate
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -1);
        LocalDate startDate = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(startDate);
        //Same but set calendar to end of previous month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate endDate = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(endDate);

        //Use calculated dates to search in database
        List<Activity> activityList = activityService.findActivitiesForProjectOfMonth(projectId,startDate, endDate );
        System.out.println("--------size of activitylist------" +activityList.size());


        int totalActivityTime = 0;

        for (Activity activity : activityList) {
            totalActivityTime = totalActivityTime + activity.getTimeSpent();
            System.out.println(activity.getTimeSpent());
        }

/*        //map found activities. key: employee id, value:sum of total time of activities
        Map<Integer, Integer> activityMap = activityList.stream()
                .collect(Collectors.groupingBy(Activity::getEmployee_id, Collectors.summingInt(Activity::getTimeSpent)));

        //round total time to nearest 15 Min
        activityMap.forEach((key, value) -> {
            System.out.println("Value before rounding" + value);
            int remainder = value % 15;
            if (remainder < 8) {
                value = value - remainder;
                activityMap.put(key, value);
            } else {
                value = value + (15 - remainder);
                activityMap.put(key, value);
            }
            System.out.println("Value after rounding" + value);
        });*/
/*
        //Convert total rounded time to hours and create invoice
        double sumOfAllActivityTimeInHours = (activityMap.values().stream().mapToDouble(Integer::doubleValue).sum()/60);
        System.out.println("Activitytime in hours" + sumOfAllActivityTimeInHours);*/

        ;
        int remainder = totalActivityTime % 15;
        if (remainder == 0) {
           remainder = 15;
        }
        int testTijd = totalActivityTime + (15 - remainder);
        double totalActivityTimeInHours = (double)(totalActivityTime + (15 - remainder))/60;

        System.out.println(totalActivityTime);
        System.out.println(remainder);
        System.out.println(testTijd);
        System.out.println(totalActivityTimeInHours);
        Project project = activityList.get(0).getProject();
        Invoice invoice = new Invoice();
        invoice.setDate(LocalDate.now());
        invoice.setProject(project);
        invoice.setTotalPrice(project.getHourlyRate() * totalActivityTimeInHours);

        return invoice;
    }

    @Override
    public Invoice getByProjectId(int id) {
        return invoiceJpaRepo.findInvoiceByProjectId(id);
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

}
