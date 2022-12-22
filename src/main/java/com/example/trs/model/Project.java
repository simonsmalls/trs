package com.example.trs.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="project")
@Table(name="projects")
public class Project {

    @SequenceGenerator(name="seqGen",sequenceName="projects_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="projects_id")
    private int id;
    @ManyToOne
    @JoinColumn (name="company_id")
    private Company client;
    @Column(name="projectname")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="hourlyrate")
    private double hourlyRate;
    @Column(name="startdate")
    private LocalDate startDate;
    @Column(name="enddate")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
