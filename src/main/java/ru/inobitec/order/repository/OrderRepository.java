package ru.inobitec.order.repository;

import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.model.OrderEntity;

public interface OrderRepository {
    OrderDTO getOrderById(Long id);

    OrderEntity addOrder(OrderDTO order);

    OrderEntity updateOrder(OrderDTO order);

    void deleteOrderById(Long id);
}
