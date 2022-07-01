package ru.inobitec.task1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
