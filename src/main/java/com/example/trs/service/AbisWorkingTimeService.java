package com.example.trs.service;

import com.example.trs.dto.ConsultantSalaryDTO;
import com.example.trs.error.ApiError;
import com.example.trs.exceptions.*;
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
import java.util.Arrays;
import java.util.List;

@Service
public class AbisWorkingTimeService implements WorkingTimeService {

    @Autowired
    private RestTemplate rt;
    String baseUrl = "http://localhost:8080/employees/workingtime";
    @Override
    public WorkingTime startWorkingTime(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException {

        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/start/" + consultantId);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity responseEntity =rt.getForEntity(uriBuilder.toUriString(),  WorkingTime.class);
            //ResponseEntity responseEntity = rt.exchange(uriBuilder.toUriString(), HttpMethod.GET, httpEntity, WorkingTime.class);

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
    public WorkingTime endWorkingTime(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotEndException {

        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/end/" + consultantId);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity responseEntity =rt.getForEntity(uriBuilder.toUriString(),  WorkingTime.class);

            return (WorkingTime) responseEntity.getBody();
        } catch (HttpStatusCodeException e){

            if (HttpStatus.NOT_FOUND == e.getStatusCode()){
                String serr = e.getResponseBodyAsString();
                ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
                throw new EmployeeNotFoundException("deze medewerker bestaat niet");
            } else if (HttpStatus.CONFLICT == e.getStatusCode()){
                String serr = e.getResponseBodyAsString();
                ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
                if (ae.getTitle().equals("geen open uren")){
                    throw new WorkingTimeCannotEndException(ae.getDescription());
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
    public List<WorkingTime> getByConsultantIdToday(int consultantId) throws JsonProcessingException, EmployeeNotFoundException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/" + consultantId);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity responseEntity =rt.getForEntity(uriBuilder.toUriString(),  WorkingTime[].class);

            WorkingTime[] list = (WorkingTime[]) responseEntity.getBody();
            return Arrays.asList(list);
        } catch (HttpStatusCodeException e){

            if (HttpStatus.NOT_FOUND == e.getStatusCode()){
                String serr = e.getResponseBodyAsString();
                ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
                throw new EmployeeNotFoundException(ae.getDescription());
            }
        }
        return null;
    }

    @Override
    public WorkingTime getOpenWorkingTimeTodayByConsultantId(int consultantId) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl+ "/open/"+consultantId);
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity responseEntity = rt.getForEntity(uriBuilder.toUriString(), WorkingTime.class);
            return (WorkingTime) responseEntity.getBody();
        } catch (HttpStatusCodeException e){
            String serr = e.getResponseBodyAsString();
            ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);

            if (HttpStatus.NOT_FOUND == e.getStatusCode()){
                throw new EmployeeNotFoundException(ae.getDescription());
            } else if (HttpStatus.CONFLICT == e.getStatusCode()){
                throw new WrongTypeException(ae.getDescription());
            }
        }
        return null;
    }

    @Override
    public List<ConsultantSalaryDTO> getSalariesOfAllConsultantsFor(int year, int month) throws JsonProcessingException, EmployeeNotFoundException, WrongTypeException {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/salaries/" + year + "/" + month);
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity responseEntity = rt.getForEntity(uriBuilder.toUriString(), ConsultantSalaryDTO[].class);
            ConsultantSalaryDTO[] list = (ConsultantSalaryDTO[]) responseEntity.getBody();

            return Arrays.asList(list);
        } catch (HttpStatusCodeException e){
            String serr = e.getResponseBodyAsString();
            ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
            if (e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new EmployeeNotFoundException(ae.getDescription());
            } else if (e.getStatusCode() == HttpStatus.CONFLICT){
                throw new WrongTypeException(ae.getDescription());
            }
        }
        return null;
    }

    @Override
    public void deleteWorkingTime(int id) throws JsonProcessingException, WorkingTimeCannotBeDeletedException {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/" + id);
            rt.exchange(uriBuilder.toUriString(), HttpMethod.DELETE, null, Void.class);
        } catch (HttpStatusCodeException e){
            String serr = e.getResponseBodyAsString();
            /*ApiError ae = new ObjectMapper().readValue(serr, ApiError.class);
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new WorkingTimeCannotBeDeletedException(ae.getDescription());

             */
        }
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
