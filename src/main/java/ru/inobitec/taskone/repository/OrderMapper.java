package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.OrderEntity;
import ru.inobitec.taskone.model.OrderItemEntity;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOrder(OrderEntity newOrderEntity);

    void addOrderItems(List<OrderItemEntity> items, Long orderId);

    void updateOrder(OrderEntity orderEntity, Long id);

    void updateOrderItems(List<OrderItemEntity> items, Long id);

    void deleteOrderById(Long id);

    void deleteOrderItemsById(Long id);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();
}
