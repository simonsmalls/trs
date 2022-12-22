package com.example.trs.model;

import javax.persistence.*;

@Entity(name="company")
@Table(name="companies")
public class Company {

    @SequenceGenerator(name="seqGen",sequenceName="companies_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="companies_id")
    private int id;
    @Column(name="companyname")
    private String companyName;

    public Company() {
    }

    public Company(String name) {
        this.companyName = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
