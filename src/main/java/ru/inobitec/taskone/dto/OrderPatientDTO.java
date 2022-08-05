package ru.inobitec.taskone.dto;

import lombok.*;
import ru.inobitec.taskone.model.PatientEntity;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderPatientDTO {
    private OrderDTO order;
    private PatientEntity patientEntity;
}
