package ru.inobitec.taskone.http;

import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Patient;

import java.util.HashMap;
import java.util.Map;


@Component
@AllArgsConstructor
public class HttpRestTempClient {
    private static final String URL_PATIENT = "http://localhost:8081/patient";


    public Patient getPatientInfoByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_PATIENT + "Name")
                .queryParam("name", name);

        ResponseEntity<Patient> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, Patient.class);

        return response.getBody();
    }

    public void addNewPatient(OrderDTO order){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> map = new HashMap<>();
        map.put("lastName", order.getCustomerName());
        map.put("phone", order.getCustomerPhone());

        ResponseEntity<Void> response = restTemplate.postForEntity(URL_PATIENT, map, Void.class);

    }
}
