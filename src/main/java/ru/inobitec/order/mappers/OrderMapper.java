package ru.inobitec.order.mappers;

import org.apache.ibatis.annotations.*;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.model.OrderItemEntity;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOrder(OrderEntity newOrder);

    void addOrderItem(OrderItemEntity item, Long orderId);

    void updateOrder(OrderEntity order);

    void updateOrderItems(List<OrderItemEntity> items);

    void deleteOrderById(Long id);

    void deleteOrderItemsById(Long id);

    OrderEntity getOrderById(Long id);

}
