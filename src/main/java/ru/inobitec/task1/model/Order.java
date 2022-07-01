package ru.inobitec.task1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private Long order_status_id;
    private String customer_name;
    private String customer_phone;
    private String customer_comment;
}
