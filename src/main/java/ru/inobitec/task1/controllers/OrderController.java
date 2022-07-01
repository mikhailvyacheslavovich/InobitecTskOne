package ru.inobitec.task1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.task1.model.Order;
import ru.inobitec.task1.service.OrderService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orderItems")
    public ResponseEntity<String> getAllOrderItems()  {
        return new ResponseEntity(orderService.getAllOrderItems(), HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@ModelAttribute Order newOrder){
        orderService.addOrder(newOrder);
        return ResponseEntity.status(HttpStatus.OK).body("Order added");
    }

}
