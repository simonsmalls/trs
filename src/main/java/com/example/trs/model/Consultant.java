package com.example.trs.model;


public class Consultant extends Employee {

    private double hourlyRate;

    public Consultant() {
    }

    public Consultant(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
