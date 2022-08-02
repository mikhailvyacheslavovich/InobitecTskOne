package ru.inobitec.taskone.http;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.model.Patient;

import java.util.HashMap;
import java.util.Map;


@Component
@AllArgsConstructor
public class RestClient {
    private static final String URL = "http://localhost:8081/";

    public Patient getPatientInfoByName(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "patientName")
                .queryParam("firstName", order.getCustomerFirstName())
                .queryParam("lastName", order.getCustomerLastName());
        //.queryParam("birthday",order.getCustomerBirthday());

        ResponseEntity<Patient> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, Patient.class);

        return response.getBody();
    }

    public void addNewPatient(Order order) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", order.getCustomerFirstName());
        map.put("lastName", order.getCustomerLastName());
        map.put("phone", order.getCustomerPhone());
        map.put("birthday", order.getCustomerBirthday());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "patient", entity, String.class);
    }

    public void updatePatient(Patient patient) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> requestBody = new HttpEntity<>(patient, headers);
        restTemplate.put(URL + "updatePatient/" + patient.getId(), requestBody);
    }
}
