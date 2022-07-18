package ru.inobitec.taskone.service;

import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.dto.OrderPatientDTO;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDTO newOrder);

    void updateOrder(OrderDTO orderUpdate, Long idOfOrder);

    List<OrderDTO> getAllOrders();

    OrderPatientDTO getOrderById(Long id);

    void deleteOrderById(Long id);
}
