package com.example.trs.dto;

public class LoginDTO {
    private String abbreviation;
    private String password;

    public LoginDTO() {
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
}
