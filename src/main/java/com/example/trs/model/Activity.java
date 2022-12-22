package com.example.trs.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Activity {

    private String description;
    private Employee employee;
    private Project project;
    private Category category;
    private LocalDateTime startActivity;
    private LocalDateTime endActivity;
    private LocalTime timeSpent;

    public Activity() {
    }

    public Activity(String description, Employee employee, Project project, Category category, LocalDateTime startActivity, LocalDateTime endActivity, LocalTime timeSpent) {
        this.description = description;
        this.employee = employee;
        this.project = project;
        this.category = category;
        this.startActivity = startActivity;
        this.endActivity = endActivity;
        this.timeSpent = timeSpent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
