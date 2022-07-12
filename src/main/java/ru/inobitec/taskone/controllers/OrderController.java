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

    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@RequestBody OrderDTO newOrder) {
        try {
            orderService.addOrder(newOrder);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("A new order has been successfully created");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while adding new order " + ex.getCause());
        }
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderUpdate, @PathVariable("id") Long id) {
        try {
            orderService.updateOrder(orderUpdate, id);
            return ResponseEntity.status(HttpStatus.OK).body("Order has been updated successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while updating new order " + ex.getCause());
        }
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.status(HttpStatus.OK).body("order deleted");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while deleting new order " + ex.getCause());
        }
    }

}
