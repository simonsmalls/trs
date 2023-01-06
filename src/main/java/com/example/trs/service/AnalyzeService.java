package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;

import java.time.LocalDate;
import java.util.List;

public interface AnalyzeService {
    List<AnalyzeDTO> findActivitiesByProjectIdAndDates(int id, LocalDate start, LocalDate end);

    List<AnalyzeDTO> findActivitiesByDates( LocalDate start, LocalDate end) ;
    List<AnalyzeDTO> findActivitiesByProjectId(int id);
    List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeIdAndDates(int pid,int eid, LocalDate start, LocalDate end) ;
    List<AnalyzeDTO> findActivitiesByEmployee_idAndDates(int id, LocalDate start, LocalDate end) ;
    List<AnalyzeDTO>  findActivitiesByEmployee_id(int id) ;
    List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeId(int project_id, int eid) ;



    }
