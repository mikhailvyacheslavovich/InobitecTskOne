package ru.inobitec.order.repository.Impl;

import lombok.RequiredArgsConstructor;
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
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO getOrderById(Long id) {
        return orderMapper.getOrderById(id).toDTO();
    }

    @Override
    @Transactional
    public void addOrder(OrderDTO order) {
        OrderEntity ord = order.toEntity();
        orderMapper.addOrder(ord);

        for (OrderItemEntity item : order.getOrderItems()){
            orderMapper.addOrderItem(item, ord.getId());
        }
    }

    @Override
    @Transactional
    public void updateOrder(OrderDTO order, Long id) {
        orderMapper.updateOrder(order.toEntity(), id);
        orderMapper.updateOrderItems(order.getOrderItems(), id);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        return orderMapper.getAllOrders();
    }
}
