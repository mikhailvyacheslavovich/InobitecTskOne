package ru.inobitec.order.repository.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.mappers.OrderMapper;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;
import ru.inobitec.order.repository.OrderRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO getOrderById(Long id) {
        OrderEntity order = orderMapper.getOrderById(id);
        if (order == null)
            return null;
        return order.toDTO();
    }

    @Override
    @Transactional
    public OrderEntity addOrder(OrderEntity order) {
        orderMapper.addOrder(order);
        order.getOrderItems().forEach(item -> orderMapper.addOrderItem(item, order.getId()));
        return order;
    }

    @Override
    @Transactional
    public OrderEntity updateOrder(OrderEntity order) {
        orderMapper.updateOrder(order);

        //альтернативный вариант апдейта
        //orderMapper.deleteOrderItemsById(order.getId());
        //order.getOrderItems().forEach(item -> orderMapper.addOrderItem(item, order.getId()));

        List<Long> itemIds = orderMapper.getAllItemsIdByOrderId(order.getId());
        List<OrderItemEntity> items = order.getOrderItems();
        items.forEach(item -> {
            if (item.getId() == null)
                orderMapper.addOrderItem(item, order.getId());
            if (itemIds.contains(item.getId())) {
                orderMapper.updateOrderItem(item);
                itemIds.remove(item.getId());
            }
        });

        itemIds.forEach(orderMapper::deleteOrderItemById);

        return order;
    }

    @Override
    @Transactional
    public Long deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        return orderMapper.deleteOrderById(id);
    }
}
