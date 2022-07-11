package ru.inobitec.taskone.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.service.OrderService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@RequestBody OrderDTO newOrder) {
        orderService.addOrder(newOrder);
        return ResponseEntity.status(HttpStatus.OK).body("A new order has been successfully created");
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderUpdate) {
        orderService.updateOrder(orderUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Order has been updated successfully");
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        return new ResponseEntity<OrderDTO>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body("order deleted");
    }

}