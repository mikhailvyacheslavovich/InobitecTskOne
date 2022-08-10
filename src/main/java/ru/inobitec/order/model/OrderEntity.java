package ru.inobitec.order.model;

import lombok.*;
import ru.inobitec.order.dto.OrderDTO;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderEntity {
    private Long id;
    private Long orderStatusId;
    private Long patientId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private List<OrderItemEntity> orderItems;

    public OrderDTO toDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderItems(getOrderItems());
        orderDTO.setCustomerComment(getCustomerComment());
        orderDTO.setCustomerPhone(getCustomerPhone());
        orderDTO.setCustomerName(getCustomerName());
        orderDTO.setOrderStatusId(getOrderStatusId());
        orderDTO.setId(getId());
        orderDTO.setPatientId(getPatientId());
        return orderDTO;
    }
}
