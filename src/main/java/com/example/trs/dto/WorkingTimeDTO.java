package com.example.trs.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingTimeDTO {
    private int id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int timeWorkedMin;
    private double consultantHourlyRate;

    public WorkingTimeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getTimeWorkedMin() {
        return timeWorkedMin;
    }

    public void setTimeWorkedMin(int timeWorkedMin) {
        this.timeWorkedMin = timeWorkedMin;
    }

    public double getConsultantHourlyRate() {
        return consultantHourlyRate;
    }

    public void setConsultantHourlyRate(double consultantHourlyRate) {
        this.consultantHourlyRate = consultantHourlyRate;
    }
}
