package ru.inobitec.taskone.dto;

import lombok.*;
import ru.inobitec.taskone.model.Patient;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderPatient {
    private OrderDTO order;
    private Patient patient;
}
