package ru.inobitec.taskone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    public void addOrder(OrderDTO newOrder){
        Orders orders = newOrder.OrderFromDto();
        mainMapper.addOrder(orders);
        Long id = orders.getId();
        for (OrderItem item : newOrder.getOrderItems()){
            item.setOrderId(id);
            mainMapper.addOrderItem(item);
        }
    }

    public void updateOrder(OrderDTO orderUpdate){
        Orders order = orderUpdate.OrderFromDto();
        for (OrderItem item : orderUpdate.getOrderItems()){
            item.setOrderId(order.getId());
            mainMapper.updateOrderItem(item);
        }
        mainMapper.updateOrder(order);
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
