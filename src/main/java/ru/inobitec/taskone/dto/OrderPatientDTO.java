package ru.inobitec.taskone.dto;

import lombok.*;
import ru.inobitec.taskone.model.Patient;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderPatientDTO {
    private OrderDTO order;
    private Patient patient;
}
