package com.example.trs.service;

import com.example.trs.error.ApiError;
import com.example.trs.exceptions.EmployeeNotFoundException;
import com.example.trs.exceptions.WorkingTimeCannotStartException;
import com.example.trs.exceptions.WrongTypeException;
import com.example.trs.model.WorkingTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbisWorkingTimeService implements WorkingTimeService {

    @Autowired
    private RestTemplate rt;
    String baseUrl = "http://localhost:8080/employees";
    @Override
    public WorkingTime startWorkingTime(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException {

        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/start/" + consultantId);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity responseEntity = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET, httpEntity, WorkingTime.class);
            return (WorkingTime) responseEntity.getBody();
        } catch (HttpStatusCodeException e){
            if (HttpStatus.NOT_FOUND == e.getStatusCode()){
                String serr = e.getResponseBodyAsString();
                ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
                throw new EmployeeNotFoundException("this employee does not exist");
            } else if (HttpStatus.CONFLICT == e.getStatusCode()){
                String serr = e.getResponseBodyAsString();
                ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
                if (ae.getTitle().equals("bestaat al")){
                    throw new WorkingTimeCannotStartException(ae.getDescription());
                } else if (ae.getTitle().equals("wrong type")){
                    throw new WrongTypeException(ae.getDescription());
                }
            }

        }
        return null;
    }

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
    public List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date) {
        return null;
    }
}
