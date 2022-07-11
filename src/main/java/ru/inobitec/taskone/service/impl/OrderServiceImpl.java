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

    @Override
    public void addOrder(OrderDTO newOrder) {
        orderMapper.addOrder(newOrder);
        orderMapper.addOrderItems(newOrder.getOrderItems(), newOrder.getId());
    }

    @Override
    public void updateOrder(OrderDTO orderUpdate, Long id) {
        orderMapper.updateOrder(orderUpdate, id);
        orderMapper.updateOrderItems(orderUpdate.getOrderItems(), id);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderById(id);
    }
}
