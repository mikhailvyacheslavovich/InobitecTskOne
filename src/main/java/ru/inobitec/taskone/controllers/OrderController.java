package ru.inobitec.taskone.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.dto.Test;
import ru.inobitec.taskone.service.OrderService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orderItems")
    public ResponseEntity<String> getAllOrderItems()  {
        return new ResponseEntity(orderService.getAllOrderItems(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@ModelAttribute OrderDTO newOrder){
        orderService.addOrder(newOrder);
        return ResponseEntity.status(HttpStatus.OK).body("Order added");
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@ModelAttribute OrderDTO orderUpdate){
        orderService.updateOrder(orderUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated");
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") String id){
        return new ResponseEntity(orderService.getOrderById(id), HttpStatus.OK);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") String id){
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body("order deleted");
    }

}
