package com.example.trs.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="invoice")
@Table(name="invoices")
public class Invoice {

    @SequenceGenerator(name="seqGen",sequenceName="invoices_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="invoices_id")
    private int id;
    @Column(name="invoicedate")
    private LocalDate date;
    @Column(name="totalprice")
    private double totalPrice;
    @ManyToOne
    @JoinColumn (name="project_id")
    private Project project;
    @Column(name="closed")
    private boolean closed;

    public Invoice() {
    }

    public Invoice(LocalDate date, double totalPrice, Project project, boolean closed) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.project = project;
        this.closed = closed;
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
