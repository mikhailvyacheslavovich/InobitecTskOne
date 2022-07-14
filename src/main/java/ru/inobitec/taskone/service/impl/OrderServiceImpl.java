package ru.inobitec.taskone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.exceptions.ResourceNotFoundException;
import ru.inobitec.taskone.repository.OrderMapper;
import ru.inobitec.taskone.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.getAllOrders();
    }
    @Override
    public OrderDTO getOrderById(Long id) {
        return orderMapper.getOrderById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order with id " + id + " not found"));
    }

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
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderById(id);
    }
}
