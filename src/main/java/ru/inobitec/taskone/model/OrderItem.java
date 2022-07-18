package ru.inobitec.taskone.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long id;
    private Long orderId;
    private String itemName;
}
