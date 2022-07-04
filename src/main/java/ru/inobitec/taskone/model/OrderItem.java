package ru.inobitec.taskone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
    private Long id;
    private Long orderId;
    private String itemName;
}
