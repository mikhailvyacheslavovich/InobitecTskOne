package ru.inobitec.order.service;

import ru.inobitec.order.dto.OrderDTO;

public interface OrderService {
    void addOrder(OrderDTO newOrder);

    void updateOrder(OrderDTO order);

    OrderDTO getOrderById(Long id);

    void deleteOrderById(Long id);
}
