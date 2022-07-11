package ru.inobitec.taskone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.inobitec.taskone.model.OrderItem;
import ru.inobitec.taskone.model.Order;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class OrderDTO {
    private Long id;
    private Integer orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    List<OrderItem> orderItems;

    public Order OrderFromDto() {
        Order order = new Order();
        order.setId(id);
        order.setOrderStatusId(orderStatusId);
        order.setCustomerComment(customerComment);
        order.setCustomerName(customerName);
        order.setCustomerPhone(customerPhone);
        return order;
    }

}