package ru.inobitec.order.service;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.Patient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@AllArgsConstructor
public class PatientService {
    private static final String URL = "http://localhost:8081/";

    public Patient getPatientByName(String firstName, String lastName, Date birthday) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "patientName")
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("birthday", birthday);

        try {
            ResponseEntity<Patient> response = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET, entity, Patient.class);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Patient getPatientById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Patient> response = restTemplate
                    .exchange(URL + "patient/" + id, HttpMethod.GET, entity, Patient.class);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long addPatient(OrderDTO order) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", order.getFirstName());
        map.put("lastName", order.getLastName());
        map.put("phone", order.getCustomerPhone());
        map.put("birthday", order.getBirthday());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        try{
            ResponseEntity<String> response = restTemplate.postForEntity(URL + "patient", entity, String.class);
            return Long.parseLong(response.getBody());
        }catch(Exception ex){
            ex.printStackTrace();
            return -1l;
        }
    }

    public void updatePatient(Patient patient) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> requestBody = new HttpEntity<>(patient, headers);
        try {
            restTemplate.put(URL + "patient", requestBody);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
