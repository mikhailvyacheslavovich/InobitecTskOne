package ru.inobitec.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ru.inobitec.order.util.StringConstants.*;


@Service
@RequiredArgsConstructor
@Log4j2
public class PatientService {

    public Patient getPatientByName(String firstName, String lastName, Date birthday) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + PATIENT_NAME)
                .queryParam(FIRST_NAME, firstName)
                .queryParam(LAST_NAME, lastName)
                .queryParam(BIRTHDAY, new SimpleDateFormat(DATE_FORMAT).format(birthday));

        try {
            ResponseEntity<Patient> response = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET, entity, Patient.class);
            return response.getBody();
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
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
                    .exchange(URL + PATIENT + id, HttpMethod.GET, entity, Patient.class);
            return response.getBody();
        } catch (Exception ex) {
            log.error(ex.getCause());
            return null;
        }
    }

    public Long addPatient(OrderDTO order) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put(FIRST_NAME, order.getFirstName());
        map.put(LAST_NAME, order.getLastName());
        map.put(PHONE, order.getCustomerPhone());
        map.put(BIRTHDAY, order.getBirthday());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(URL + PATIENT, entity, String.class);
            return Long.parseLong(response.getBody());
        } catch (Exception ex) {
            log.error(ex.getCause());
            return -1l;
        }
    }

    public void updatePatient(Patient patient) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> requestBody = new HttpEntity<>(patient, headers);
        try {
            restTemplate.put(URL + PATIENT, requestBody);
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
    }
}
