package ru.inobitec.order.repository.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.mappers.OrderMapper;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;
import ru.inobitec.order.repository.OrderRepository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO getOrderById(Long id) throws RuntimeException {
        return orderMapper.getOrderById(id).toDTO();
    }

    @Override
    @Transactional
    public OrderEntity addOrder(OrderDTO order) throws RuntimeException {
        OrderEntity ord = order.toEntity();
        orderMapper.addOrder(ord);

        for (OrderItemEntity item : order.getOrderItems()) {
            orderMapper.addOrderItem(item, ord.getId());
        }
        return ord;
    }

    @Override
    @Transactional
    public OrderEntity updateOrder(OrderDTO order) throws RuntimeException {
        OrderEntity orderEntity = order.toEntity();
        orderMapper.updateOrder(orderEntity);
        orderMapper.updateOrderItems(orderEntity.getOrderItems());
        return orderEntity;
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) throws RuntimeException {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }
}
