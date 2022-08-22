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

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO getOrderById(Long id) {
        try {
            return orderMapper.getOrderById(id).toDTO();
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Transactional
    public OrderEntity addOrder(OrderEntity order) {
        try {
            orderMapper.addOrder(order);
            order.getOrderItems().forEach(item->{
                orderMapper.addOrderItem(item, order.getId());
            });
            return order;
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Transactional
    public OrderEntity updateOrder(OrderEntity order) {
        try {
            orderMapper.updateOrder(order);
            orderMapper.updateOrderItems(order.getOrderItems());
            return order;
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        try {
            orderMapper.deleteOrderItemsById(id);
            orderMapper.deleteOrderById(id);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }
}
