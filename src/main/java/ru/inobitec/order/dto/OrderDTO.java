package ru.inobitec.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderDTO {
    private Long id;
    private Long orderStatusId;

    private Long patientId;

    private String customerName;
    private String customerPhone;
    private String customerComment;
    private List<OrderItemEntity> orderItems;
    private Patient patient;

    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @JsonFormat(locale = "ru", timezone = "GMT+3", pattern = "dd-mm-yyyy")
    private Date birthday;

    public OrderEntity toEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatusId(getOrderStatusId());
        orderEntity.setCustomerComment(getCustomerComment());
        orderEntity.setCustomerName(getCustomerName());
        orderEntity.setOrderItems(getOrderItems());
        orderEntity.setPatientId(getPatientId());
        orderEntity.setId(getId());
        orderEntity.setCustomerPhone(getCustomerPhone());
        return orderEntity;
    }
}
