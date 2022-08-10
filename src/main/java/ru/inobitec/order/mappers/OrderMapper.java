package ru.inobitec.order.mappers;

import org.apache.ibatis.annotations.*;
import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOrder(OrderEntity newOrder);

    void addOrderItems(List<OrderItemEntity> items, Long orderId);

    void addOrderItem(OrderItemEntity item, Long orderId);

    void updateOrder(OrderEntity order, Long id);

    void updateOrderItems(List<OrderItemEntity> items, Long id);

    void deleteOrderById(Long id);

    void deleteOrderItemsById(Long id);

    OrderEntity getOrderById(Long id);

    List<OrderDTO> getAllOrders();
}
