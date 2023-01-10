package com.example.trs.service;

import com.example.trs.dto.EmployeeDTO;
import com.example.trs.dto.LoginDTO;
import com.example.trs.error.ApiError;
import com.example.trs.exceptions.EmployeeNotFoundException;
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
    public List<EmployeeDTO> getAll() {
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl);

        ResponseEntity g =rt.getForEntity(uriBuilder.toUriString(), EmployeeDTO[].class);
        EmployeeDTO[] list = (EmployeeDTO[]) g.getBody();

        return Arrays.asList(list);
    }

    @Override
    public void checkIfEmployeeExists(int id) throws EmployeeNotFoundException {
        try {
            getAll().get(id -1); // checks if employee exists
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmployeeNotFoundException("Persoon niet gevonden");
        }
    }


    @Override
    public EmployeeDTO checkLogin(String abbr, String pass) throws EmployeeNotFoundException, JsonProcessingException {
        try {
            UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl(baseUrl+"/login");

            HttpHeaders headers = new HttpHeaders();
            LoginDTO login=new LoginDTO();
            login.setPassword(pass);
            login.setAbbreviation(abbr);
            HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(login,headers);
            ResponseEntity g =rt.exchange(uriBuilder.toUriString(), HttpMethod.POST,requestEntity, EmployeeDTO.class);

            return (EmployeeDTO) g.getBody();


        } catch (HttpStatusCodeException e) {


            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                String serr = e.getResponseBodyAsString();
                //System.out.println(serr);
                ApiError ae=new ObjectMapper().readValue(serr,ApiError.class);
                throw new EmployeeNotFoundException("gebruikersnaam: "+abbr+" en paswoord matchen niet");

            } else {
                String serr = e.getResponseBodyAsString();
            }
        }
        return null;
    }

    @Override
    public boolean hasRole(int employeeId, String role) {
        return false;
    }
}
