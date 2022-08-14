package ru.inobitec.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.inobitec.order.model.OrderEntity;

import java.util.Date;
import java.util.List;

import static ru.inobitec.order.util.StringConstants.DATE_FORMAT;

@Data
@RequiredArgsConstructor
@XmlRootElement
public class OrderDTO {
    private Long id;
    private Long orderStatusId;
    private Long patientId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private List<OrderItemDTO> orderItems;
    private Patient patient;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = DATE_FORMAT)
    @JsonFormat(locale = "ru", timezone = "GMT+3", pattern = DATE_FORMAT)
    private Date birthday;

    public OrderEntity toEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatusId(getOrderStatusId());
        orderEntity.setCustomerComment(getCustomerComment());
        orderEntity.setCustomerName(getCustomerName());
        orderEntity.setOrderItems(orderItems.stream().map(OrderItemDTO::toEntity).toList());
        orderEntity.setPatientId(getPatientId());
        orderEntity.setId(getId());
        orderEntity.setCustomerPhone(getCustomerPhone());
        return orderEntity;
    }
}
