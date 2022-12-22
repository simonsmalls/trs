package com.example.trs.model;


import javax.persistence.*;

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
    @Column(name = "starttime")
    private LocalDateTime startActivity;
    @Column(name = "endtime")
    private LocalDateTime endActivity;
    @Column(name = "timespent")
    private LocalTime timeSpent;

    public Activity() {
    }

    public Activity(String description, Employee employee, Project project, Category category, LocalDateTime startActivity, LocalDateTime endActivity, LocalTime timeSpent) {
        this.description = description;
        this.employee_id = employee.getId();
        this.project = project;
        this.category = category;
        this.startActivity = startActivity;
        this.endActivity = endActivity;
        this.timeSpent = timeSpent;
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

    public LocalDateTime getStartActivity() {
        return startActivity;
    }

    public void setStartActivity(LocalDateTime startActivity) {
        this.startActivity = startActivity;
    }

    public LocalDateTime getEndActivity() {
        return endActivity;
    }

    public void setEndActivity(LocalDateTime endActivity) {
        this.endActivity = endActivity;
    }

    public LocalTime getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(LocalTime timeSpent) {
        this.timeSpent = timeSpent;
    }
}
