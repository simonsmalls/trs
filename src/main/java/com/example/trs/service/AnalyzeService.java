package com.example.trs.service;

import com.example.trs.dto.AnalyzeDTO;
import com.example.trs.exceptions.ProjectNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface AnalyzeService {
    List<AnalyzeDTO> findActivitiesByProjectIdAndDates(int id, LocalDate start, LocalDate end) throws ProjectNotFoundException;

    List<AnalyzeDTO> findActivitiesByDates( LocalDate start, LocalDate end) throws ProjectNotFoundException;
    List<AnalyzeDTO> findActivitiesByProjectId(int id) throws ProjectNotFoundException;
    List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeIdAndDates(int pid,int eid, LocalDate start, LocalDate end) throws ProjectNotFoundException;
    List<AnalyzeDTO> findActivitiesByEmployee_idAndDates(int id, LocalDate start, LocalDate end) throws ProjectNotFoundException;
    List<AnalyzeDTO>  findActivitiesByEmployee_id(int id) throws ProjectNotFoundException;
    List<AnalyzeDTO> findActivitiesByProjectIdAndEmployeeId(int project_id, int eid) throws ProjectNotFoundException;



    }
