package ru.inobitec.order.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageDTO {
    private String command;
    private OrderDTO orderDTO;
}
