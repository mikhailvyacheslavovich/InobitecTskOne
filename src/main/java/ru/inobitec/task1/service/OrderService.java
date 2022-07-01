package ru.inobitec.task1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inobitec.task1.model.Order;
import ru.inobitec.task1.model.OrderItem;
import ru.inobitec.task1.repository.MainMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    MainMapper mainMapper;

    public List<OrderItem> getAllOrderItems(){
        return mainMapper.getAllOrderItems();
    }

    public void addOrder(Order newOrder){
        mainMapper.addOrder(newOrder);
    }

}
