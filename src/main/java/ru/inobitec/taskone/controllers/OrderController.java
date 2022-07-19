package ru.inobitec.taskone.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;

import ru.inobitec.taskone.dto.OrderPatientDTO;
import ru.inobitec.taskone.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public OrderPatientDTO getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/order")
    public String addOrder(@RequestBody OrderDTO newOrder) {
        orderService.addOrder(newOrder);
        return "new order was created successfully";
    }

    @PutMapping("/updateOrder/{id}")
    public String updateOrder(@RequestBody OrderDTO orderUpdate,
                              @PathVariable("id") Long id) {
        orderService.updateOrder(orderUpdate, id);
        return "order has been updated successfully";
    }

    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "order deleted successfully";
    }
}
