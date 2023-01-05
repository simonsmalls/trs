package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AnalyzeService {
    List<AnalyzeDTO> findActivitiesByProjectId(int id, LocalDate start, LocalDate end);
}
