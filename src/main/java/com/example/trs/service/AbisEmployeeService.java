package com.example.trs.service;

import com.example.trs.model.Consultant;
import com.example.trs.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public boolean checkLogin(String abbreviation, String password) {
        return false;
    }

    @Override
    public boolean hasRole(int employeeId, String role) {
        return false;
    }
}
