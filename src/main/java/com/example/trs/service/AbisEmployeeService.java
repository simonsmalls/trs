package com.example.trs.service;

import com.example.trs.dto.LoginDTO;
import com.example.trs.error.ApiError;
import com.example.trs.exception.EmployeeNotFoundException;
import com.example.trs.model.Consultant;
import com.example.trs.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class AbisEmployeeService implements EmployeeService {

    @Autowired
    private RestTemplate rt;

    String baseUrl="http://localhost:8080/employees";
    @Override
    public List<Employee> getAll() {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

        ResponseEntity g =rt.getForEntity(uriBuilder.toUriString(), Employee[].class);
        Employee[] list = (Employee[]) g.getBody();

        return Arrays.asList(list);
    }

    @Override
    public Employee getById(int id) {
        return null;
    }

    @Override
    public List<Consultant> getAllConsultants() {
        return null;
    }

    @Override
    public List<Employee> getAllNoneConsultants() {
        return null;
    }

    @Override
    public List<Employee> getManagers() {
        return null;
    }

    @Override
    public List<Employee> getAccountants() {
        return null;
    }

    @Override
    public List<Employee> getTeachers() {
        return null;
    }

    @Override
    public Employee checkLogin(String abbr, String pass) throws EmployeeNotFoundException, JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/login");

            HttpHeaders headers = new HttpHeaders();
            LoginDTO login=new LoginDTO();
            login.setPassword(pass);
            login.setAbbreviation(abbr);
            HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(login,headers);
            ResponseEntity g =rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, Employee.class);

            return (Employee) g.getBody();


        } catch (HttpStatusCodeException e) {
            System.out.println("in catch");

            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                System.out.println(ae.getDescription());
                throw new EmployeeNotFoundException("gebruikersnaam en paswoord matchen niet");

            } else {
                String serr = e.getResponseBodyAsString();
                System.out.println(serr);
            }
        }
        return null;
    }

    @Override
    public boolean hasRole(int employeeId, String role) {
        return false;
    }
}
