package ru.inobitec.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.inobitec.order.model.OrderItemEntity;

@Data
@RequiredArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private String itemName;

    public OrderItemEntity toEntity() {
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setOrderId(getOrderId());
        orderItem.setItemName(getItemName());
        orderItem.setId(getId());
        return orderItem;
    }
}
