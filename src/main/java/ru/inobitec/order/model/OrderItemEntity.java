package ru.inobitec.order.model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class OrderItemEntity {
    private Long id;
    private Long orderId;
    private String itemName;
}
