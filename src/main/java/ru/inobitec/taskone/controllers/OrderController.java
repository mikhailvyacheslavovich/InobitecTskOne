package ru.inobitec.taskone.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.model.Orders;
import ru.inobitec.taskone.service.OrderService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orderItems")
    public ResponseEntity<String> getAllOrderItems()  {
        return new ResponseEntity(orderService.getAllOrderItems(), HttpStatus.OK);
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
