package ru.inobitec.order.repository;

import ru.inobitec.order.dto.OrderDTO;

import java.util.List;

public interface OrderRepository {
    OrderDTO getOrderById(Long id);

    void addOrder(OrderDTO order);

    void updateOrder(OrderDTO order, Long id);

    void deleteOrderById(Long id);

    List<OrderDTO> getAllOrders();
}
