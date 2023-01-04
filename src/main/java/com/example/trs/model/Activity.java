package com.example.trs.model;


import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name="activity")
@Table(name="activities")
public class Activity {

    @SequenceGenerator(name="seqGen",sequenceName="activities_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="activities_id")
    private int id;
    @Column(name = "description")
    private String description;
    @JoinColumn (name="employee_id")
    private int employee_id;
    @ManyToOne
    @JoinColumn (name="project_id")
    private Project project;
    @ManyToOne
    @JoinColumn (name="category_id")
    private Category category;
    @Column(name = "startdate")
    private LocalDate startDate ;
    @Column(name = "starttime")
    private LocalTime startTime;

    @Column(name = "endtime")
    private LocalTime endTime;
    //todo timespent in data base and how to set it
    @Column(name = "timespent")
    private int timeSpent;

    public Activity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
