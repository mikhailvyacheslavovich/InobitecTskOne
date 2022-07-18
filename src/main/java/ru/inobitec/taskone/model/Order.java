package ru.inobitec.taskone.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Long id;
    private Integer orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
}
