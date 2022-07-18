package ru.inobitec.taskone.dto;

import lombok.*;
import ru.inobitec.taskone.model.OrderItem;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.model.Patient;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderDTO {
    private Order order;
    private List<OrderItem> orderItems;


    public boolean orderPatientEquals(Patient patient) {
        return getOrder().getCustomerFirstName().equals(patient.getFirstName()) &&
                getOrder().getCustomerLastName().equals(patient.getLastName()) &&
                getOrder().getCustomerBirthday().equals(patient.getBirthday());
    }
}
