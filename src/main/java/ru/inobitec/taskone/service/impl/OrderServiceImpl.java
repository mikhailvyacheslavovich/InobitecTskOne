package ru.inobitec.taskone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.repository.OrderMapper;
import ru.inobitec.taskone.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public void addOrder(OrderDTO newOrder) {
        Order order = newOrder.OrderFromDto();
        orderMapper.addOrder(order);
        Long id = order.getId();
        newOrder.getOrderItems().forEach((item) -> {
            item.setOrderId(id);
            orderMapper.addOrderItem(item);
        });
    }

    public void updateOrder(OrderDTO orderUpdate) {
        Order order = orderUpdate.OrderFromDto();
        Long id = order.getId();
        orderUpdate.getOrderItems().forEach((item) -> {
            item.setOrderId(id);
            orderMapper.updateOrderItem(item);
        });
        orderMapper.updateOrder(order);
    }

    public OrderDTO getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }

    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }
}
