package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.*;
import ru.inobitec.taskone.model.Orders;
import ru.inobitec.taskone.model.OrderItem;

import java.util.List;

@Mapper
public interface MainMapper {

    @Select("select id, order_id, item_name from order_item")
    List<OrderItem> getAllOrderItems();

    @Select("Select id, order_status_id as orderStatusId, customer_name as customerName, customer_phone as customerPhone," +
            " customer_comment as customerComment  from \"order\" where id=#{id}")
    Orders selectById(int id);

    @Select("select id, order_id as orderId, item_name as itemName from order_item where order_id = #{id}")
    List<OrderItem> getOrderItemsById(int id);

    @Delete("delete from \"order\" where id = #{id}")
    void deleteOrderById(int id);

    @Delete("delete from order_item where order_id = #{id}")
    void deleteOrderItemsById(int id);
}
