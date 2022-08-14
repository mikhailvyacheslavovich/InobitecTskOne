package ru.inobitec.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static ru.inobitec.order.util.StringConstants.DATE_FORMAT;

@Data
@RequiredArgsConstructor
public class Patient {
    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private Byte genderId;
    @DateTimeFormat(pattern = DATE_FORMAT)
    @JsonFormat(locale = "ru", timezone = "GMT+3", pattern = DATE_FORMAT)
    private Date birthday;
    private String phone;
    private String email;
    private String address;
}