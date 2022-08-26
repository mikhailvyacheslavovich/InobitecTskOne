package ru.inobitec.order.repository;

import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.model.OrderEntity;

public interface OrderRepository {
    OrderDTO getOrderById(Long id);

    OrderEntity addOrder(OrderEntity order);

    OrderEntity updateOrder(OrderEntity order);

    Long deleteOrderById(Long id);
}
