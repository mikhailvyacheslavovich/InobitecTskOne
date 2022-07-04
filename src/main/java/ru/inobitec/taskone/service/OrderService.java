package ru.inobitec.taskone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.OrderItem;
import ru.inobitec.taskone.model.Orders;
import ru.inobitec.taskone.repository.MainMapper;

import java.sql.SQLOutput;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final MainMapper mainMapper;

    public List<OrderItem> getAllOrderItems(){
        return mainMapper.getAllOrderItems();
    }

    public OrderDTO getOrderById(String id){
        Orders order = mainMapper.selectById(Integer.parseInt(id));
        List <OrderItem> items = mainMapper.getOrderItemsById(Integer.parseInt(id));
        return OrderDTO.buildOrderDto(order, items);
    }

    public void deleteOrderById(String id){
        int ident = Integer.parseInt(id);
        mainMapper.deleteOrderItemsById(ident);
        mainMapper.deleteOrderById(ident);
    }
}
