package ru.inobitec.taskone.service;

import ru.inobitec.taskone.dto.OrderDTO;

public interface OrderService {
    public void addOrder(OrderDTO newOrder);

    public void updateOrder(OrderDTO orderUpdate);

    public OrderDTO getOrderById(Long id);

    public void deleteOrderById(Long id);
}
