package ru.inobitec.taskone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.inobitec.taskone.dto.OrderDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String command;
    private OrderDTO orderDTO;
}
