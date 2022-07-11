package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Order;
import ru.inobitec.taskone.model.OrderItem;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO \"order\"(order_status_id,  customer_name,  customer_phone, customer_comment)" +
            "VALUES (#{orderStatusId}, #{customerName}, #{customerPhone}, #{customerComment})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addOrder(Order newOrder);

    @Insert("INSERT INTO order_item(order_id, item_name) VALUES(#{orderId}, #{itemName})")
    void addOrderItem(OrderItem orderItem);

    @Update("UPDATE \"order\" SET order_status_id = #{orderStatusId}, customer_name = #{customerName}," +
            " customer_phone = #{customerPhone}, customer_comment = #{customerComment} WHERE id = #{id}")
    void updateOrder(Order order);

    @Update("UPDATE order_item SET order_id = #{orderId}, item_name = #{itemName} " +
            "WHERE id = #{id}")
    void updateOrderItem(OrderItem orderItem);

    @Delete("DELETE FROM \"order\" WHERE id = #{id}")
    void deleteOrderById(Long id);

    @Delete("DELETE FROM order_item WHERE order_id = #{id}")
    void deleteOrderItemsById(Long id);

    OrderDTO getOrderById(Long id);
}
