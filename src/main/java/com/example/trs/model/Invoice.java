package com.example.trs.model;

import java.time.LocalDate;

public class Invoice {

    private LocalDate date;
    private double totalPrice;
    private Project project;
    private boolean closed;

    public Invoice() {
    }

    public Invoice(LocalDate date, double totalPrice, Project project, boolean closed) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.project = project;
        this.closed = closed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
