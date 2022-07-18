package ru.inobitec.taskone.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Long id;
    private Integer orderStatusId;
    private String customerFirstName;
    private String customerLastName;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date customerBirthday;
    private String customerPhone;
    private String customerComment;
}
