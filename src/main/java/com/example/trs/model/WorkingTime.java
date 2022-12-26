package com.example.trs.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingTime {

    private int id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int timeWorkedMin;
    private Consultant consultant;

    public WorkingTime() {
    }

    public WorkingTime(LocalDate date, LocalTime startTime, LocalTime endTime, int timeWorkedMin, Consultant consultant) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeWorkedMin = timeWorkedMin;
        this.consultant = consultant;
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

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }
}
