package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.model.OrderItem;

import java.util.List;

@Mapper
public interface OrderMapper {

    Long addOrder(OrderDTO newOrder);
    void addOrderItems(List<OrderItem> items, Long orderId);

    @Update("UPDATE \"order\" SET order_status_id = #{orderStatusId}, customer_name = #{customerName}," +
            " customer_phone = #{customerPhone}, customer_comment = #{customerComment} WHERE id = #{id}")
    void updateOrder(Order order);

    @Update("UPDATE order_item SET order_id = #{orderId}, item_name = #{itemName} " +
            "WHERE id = #{id}")
    void updateOrderItem(OrderItem orderItem);

    void deleteOrderById(Long id);
    OrderDTO getOrderById(Long id);
}
