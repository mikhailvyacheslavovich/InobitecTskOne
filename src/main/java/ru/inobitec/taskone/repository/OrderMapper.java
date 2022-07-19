package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.model.OrderItem;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    void addOrder(Order newOrder);

    void addOrderItems(List<OrderItem> items, Long orderId);

    void updateOrder(OrderDTO order, Long idOfOrder);

    void updateOrderItems(List<OrderItem> items, Long idOfOrder);


    void deleteOrderById(Long id);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();
}
