package com.example.trs.service;

import com.example.trs.model.WorkingTime;

import java.time.LocalDate;
import java.util.List;

public interface WorkingTimeService {

    List<WorkingTime> getAll();
    WorkingTime getById(int id);
    List<WorkingTime> getByConsultantId(int consultantId);
    List<WorkingTime> getByDate(LocalDate date);
    WorkingTime getByConsultantIdAndDate(int consultantId, LocalDate date);

}
