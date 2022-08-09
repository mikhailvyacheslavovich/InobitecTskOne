package ru.inobitec.order.dto;

import lombok.*;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;
import ru.inobitec.order.model.PatientEntity;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderDTO {
    private OrderEntity orderEntity;
    private List<OrderItemEntity> orderItems;


    public boolean orderPatientEquals(PatientEntity patientEntity) {
        return getOrderEntity().getCustomerFirstName().equals(patientEntity.getFirstName()) &&
                getOrderEntity().getCustomerLastName().equals(patientEntity.getLastName()) &&
                getOrderEntity().getCustomerBirthday().equals(patientEntity.getBirthday());
    }
}
