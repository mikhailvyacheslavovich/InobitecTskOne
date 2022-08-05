package ru.inobitec.taskone.model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class OrderItemEntity {
    private Long id;
    private Long orderId;
    private String itemName;
}
