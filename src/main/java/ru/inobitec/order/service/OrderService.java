package ru.inobitec.order.service;

import ru.inobitec.order.dto.OrderDTO;

public interface OrderService {
    void addOrder(OrderDTO order);

    Long updateOrder(OrderDTO order);

    OrderDTO getOrderById(Long id);

    Long deleteOrderById(Long id);
}
