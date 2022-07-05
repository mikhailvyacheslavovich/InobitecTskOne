package ru.inobitec.taskone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.OrderItem;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.repository.OrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

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
        Order order = orderMapper.selectById(id);
        List<OrderItem> items = orderMapper.getOrderItemsById(id);
        return OrderDTO.buildOrderDto(order, items);
    }

    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }
}
