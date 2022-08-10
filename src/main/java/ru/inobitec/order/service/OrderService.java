package ru.inobitec.order.service;

import ru.inobitec.order.dto.OrderDTO;


import java.util.List;

public interface OrderService {
    void addOrder(OrderDTO newOrder);

    void updateOrder(OrderDTO orderUpdate, Long idOfOrder);

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(Long id);

    void deleteOrderById(Long id);
}
