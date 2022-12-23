package com.example.trs.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingTimeDTO {
    private int id;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int timeWorkedMin;
    private int consultantId;
    private String consultantName;
}
