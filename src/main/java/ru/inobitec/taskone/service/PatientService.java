package ru.inobitec.taskone.service;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.inobitec.taskone.model.OrderEntity;
import ru.inobitec.taskone.model.PatientEntity;

import java.util.HashMap;
import java.util.Map;


@Component
@AllArgsConstructor
public class PatientService {
    private static final String URL = "http://localhost:8081/";

    public PatientEntity getPatientInfoByName(OrderEntity orderEntity) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "patientName")
                .queryParam("firstName", orderEntity.getCustomerFirstName())
                .queryParam("lastName", orderEntity.getCustomerLastName())
                .queryParam("birthday", orderEntity.getCustomerBirthday());

        ResponseEntity<PatientEntity> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, PatientEntity.class);

        return response.getBody();
    }

    public void addNewPatient(OrderEntity orderEntity) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", orderEntity.getCustomerFirstName());
        map.put("lastName", orderEntity.getCustomerLastName());
        map.put("phone", orderEntity.getCustomerPhone());
        map.put("birthday", orderEntity.getCustomerBirthday());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "patient", entity, String.class);
    }

    public void updatePatient(PatientEntity patientEntity) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PatientEntity> requestBody = new HttpEntity<>(patientEntity, headers);
        restTemplate.put(URL + "updatePatient/" + patientEntity.getId(), requestBody);
    }
}
