package com.example.trs.model;

import java.util.List;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String abbreviation;
    private String password;

    private List<String> roles;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String abbreviation, String password, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.abbreviation = abbreviation;
        this.password = password;
        this.roles = roles;
    }

    public boolean hasRole(String role){
        return roles.contains(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
