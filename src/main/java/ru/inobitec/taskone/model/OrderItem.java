package ru.inobitec.taskone.model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class OrderItem {
    private Long id;
    private Long orderId;
    private String itemName;
}
