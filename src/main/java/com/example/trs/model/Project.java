package com.example.trs.model;

import java.time.LocalDate;

public class Project {

    private Company client;
    private String name;
    private String description;
    private double hourlyRate;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project() {
    }

    public Project(Company client, String name, String description, double hourlyRate, LocalDate startDate, LocalDate endDate) {
        this.client = client;
        this.name = name;
        this.description = description;
        this.hourlyRate = hourlyRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Company getClient() {
        return client;
    }

    public void setClient(Company client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
