package ru.inobitec.taskone.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    Long id;
    Long orderId;
    private String itemName;
}
