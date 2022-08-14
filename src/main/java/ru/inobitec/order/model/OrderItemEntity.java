package ru.inobitec.order.model;

import lombok.*;
import ru.inobitec.order.dto.OrderItemDTO;

@Data
@RequiredArgsConstructor
public class OrderItemEntity {
    private Long id;
    private Long orderId;
    private String itemName;

    public OrderItemDTO toDTO() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(getId());
        orderItemDTO.setOrderId(getOrderId());
        orderItemDTO.setItemName(getItemName());
        return orderItemDTO;
    }
}
