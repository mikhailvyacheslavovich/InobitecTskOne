package ru.inobitec.taskone.dto;

import lombok.*;
import ru.inobitec.taskone.model.OrderItem;
import ru.inobitec.taskone.model.Order;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderDTO {
    private Long id;
    private Integer orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    List<OrderItem> orderItems;
}
