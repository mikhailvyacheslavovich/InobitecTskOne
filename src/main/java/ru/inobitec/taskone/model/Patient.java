package ru.inobitec.taskone.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private Byte genderId;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthday;
    private String phone;
    private String email;
    private String address;
}
