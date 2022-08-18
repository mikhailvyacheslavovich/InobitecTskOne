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
    private String customerComment;
    private List<OrderItemEntity> orderItems;

    public OrderDTO toDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderItems(orderItems.stream().map(OrderItemEntity::toDTO).toList());
        orderDTO.setCustomerComment(getCustomerComment());
        orderDTO.setOrderStatusId(getOrderStatusId());
        orderDTO.setId(getId());
        orderDTO.setPatientId(getPatientId());
        return orderDTO;
    }
}
