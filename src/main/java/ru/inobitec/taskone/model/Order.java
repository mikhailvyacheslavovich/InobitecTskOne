package ru.inobitec.taskone.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Long id;
    private Integer orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
}
