package ru.inobitec.taskone.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class OrderSessionEntity {
    private Long id;
    private String sessionId;
    private Integer timeoutMinutes;
    private Date startTime;
}
