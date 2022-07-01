package ru.inobitec.task1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long order_id;
    private String item_name;
}
