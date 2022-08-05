package ru.inobitec.taskone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity {
    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private Byte genderId;
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @JsonFormat(locale="ru", timezone="GMT+3", pattern="dd-mm-yyyy")
    private Date birthday;
    private String phone;
    private String email;
    private String address;
}
