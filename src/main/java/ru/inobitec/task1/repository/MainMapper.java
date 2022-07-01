package ru.inobitec.task1.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.inobitec.task1.model.OrderItem;

import java.util.List;

@Mapper
public interface MainMapper {

    @Select("select id, order_id, item_name from order_item")
    List<OrderItem> getAllOrderItems();

}
