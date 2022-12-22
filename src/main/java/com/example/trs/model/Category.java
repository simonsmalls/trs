package com.example.trs.model;

import javax.persistence.*;

@Entity(name="category")
@Table(name="categories")
public class Category {

    @SequenceGenerator(name="seqGen",sequenceName="categories_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="categories_id")
    private int id;
    @Column(name = "categoryname")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
