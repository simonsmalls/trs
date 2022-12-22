package com.example.trs.service;

import com.example.trs.model.WorkingTime;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbisWorkingTimeService implements WorkingTimeService {
    @Override
    public List<WorkingTime> getAll() {
        return null;
    }

    @Override
    public WorkingTime getById(int id) {
        return null;
    }

    @Override
    public List<WorkingTime> getByConsultantId(int consultantId) {
        return null;
    }

    @Override
    public List<WorkingTime> getByDate(LocalDate date) {
        return null;
    }

    @Override
    public WorkingTime getByConsultantIdAndDate(int consultantId, LocalDate date) {
        return null;
    }
}
