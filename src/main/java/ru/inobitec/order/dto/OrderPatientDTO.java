package ru.inobitec.order.dto;

import lombok.*;
import ru.inobitec.order.model.PatientEntity;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderPatientDTO {
    private OrderDTO order;
    private PatientEntity patientEntity;
}
