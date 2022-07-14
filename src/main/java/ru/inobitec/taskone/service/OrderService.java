package ru.inobitec.taskone.service;

import ru.inobitec.taskone.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    public void addOrder(OrderDTO newOrder);

    public void updateOrder(OrderDTO orderUpdate, Long idOfOrder);

    List<OrderDTO> getAllOrders();

    public OrderDTO getOrderById(Long id);

    public void deleteOrderById(Long id);
}
