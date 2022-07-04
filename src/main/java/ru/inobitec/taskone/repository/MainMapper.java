package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.*;
import ru.inobitec.taskone.model.Orders;
import ru.inobitec.taskone.model.OrderItem;

import java.util.List;

@Mapper
public interface MainMapper {

    @Select("select id, order_id, item_name from order_item")
    List<OrderItem> getAllOrderItems();

    @Insert("insert into \"order\"(order_status_id,  customer_name,  customer_phone, customer_comment)" +
            "values (#{orderStatusId}, #{customerName}, #{customerPhone}, #{customerComment})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addOrder(Orders newOrder);

    @Insert("insert into order_item(order_id, item_name) values(#{orderId}, #{itemName})")
    void addOrderItem(OrderItem orderItem);

    @Update("update \"order\" set order_status_id = #{orderStatusId}, customer_name = #{customerName}," +
            " customer_phone = #{customerPhone}, customer_comment = #{customerComment} where id = #{id}")
    void updateOrder(Orders order);

    @Update("update order_item set order_id = #{orderId}, item_name = #{itemName} where id = #{id}")
    void updateOrderItem(OrderItem orderItem);

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
