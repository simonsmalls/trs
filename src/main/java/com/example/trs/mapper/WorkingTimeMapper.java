package com.example.trs.mapper;

import com.example.trs.dto.WorkingTimeDTO;
import com.example.trs.model.WorkingTime;

public class WorkingTimeMapper {

    public static WorkingTimeDTO toDTO(WorkingTime time){
        if (time == null) return null;

        WorkingTimeDTO dto = new WorkingTimeDTO();
        dto.setId(time.getId());
        dto.setDate(time.getDate());
        dto.setTimeWorkedMin(time.getTimeWorkedMin());
        dto.setStartTime(time.getStartTime());
        dto.setEndTime(time.getEndTime());
        dto.setConsultantHourlyRate(time.getConsultant().getHourlyRate());

        return dto;
    }
}
