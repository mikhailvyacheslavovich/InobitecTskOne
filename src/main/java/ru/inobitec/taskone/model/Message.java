package ru.inobitec.taskone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.inobitec.taskone.dto.OrderDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String command;
    private OrderDTO orderDTO;
}
