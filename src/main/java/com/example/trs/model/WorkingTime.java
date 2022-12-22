package com.example.trs.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingTime {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime timeWorked;
    private Consultant consultant;

    public WorkingTime() {
    }

    public WorkingTime(LocalDate date, LocalTime startTime, LocalTime endTime, LocalTime timeWorked, Consultant consultant) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeWorked = timeWorked;
        this.consultant = consultant;
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

    public LocalTime getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(LocalTime timeWorked) {
        this.timeWorked = timeWorked;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }
}
