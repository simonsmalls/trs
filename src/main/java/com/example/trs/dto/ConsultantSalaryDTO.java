package com.example.trs.dto;

public class ConsultantSalaryDTO {
    private int consultantId;
    private String name;
    private String abbreviation;
    private double salary;
    private long minutesWorked;
    private double hourlyRate;

    public ConsultantSalaryDTO() {
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getMinutesWorked() {
        return minutesWorked;
    }

    public void setMinutesWorked(long minutesWorked) {
        this.minutesWorked = minutesWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
