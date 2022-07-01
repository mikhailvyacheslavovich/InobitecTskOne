package ru.inobitec.task1.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import ru.inobitec.task1.model.Order;
import ru.inobitec.task1.model.OrderItem;

import java.util.List;

@Mapper
public interface MainMapper {

    @Select("select id, order_id, item_name from order_item")
    List<OrderItem> getAllOrderItems();


    @Insert("insert into orders(order_status_id, customer_name, customer_phone, customer_comment)" +
            "values (#{order_status_id}, #{customer_name}, #{customer_phone}, #{customer_comment})")
    int addOrder(Order newOrder);

}
