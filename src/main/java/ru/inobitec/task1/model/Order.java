package ru.inobitec.task1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long id;
    private Integer order_status_id;
    private String customer_name;
    private String customer_phone;
    private String customer_comment;
}
