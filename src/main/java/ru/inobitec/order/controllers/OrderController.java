package ru.inobitec.order.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.order.dto.OrderDTO;

import ru.inobitec.order.service.OrderService;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ApiOperation(value = "Получение сведений об ордере",
            notes = "Получить сведения об ордере по его id")
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Создание нового ордера",
            notes = "Создается ордер здесь и в patient")
    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@RequestBody OrderDTO order) {
        try {
            orderService.addOrder(order);
            return new ResponseEntity<>("new order was created successfully", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("error while creating order " + ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Изменение ордера",
            notes = "также обновляется информация в patient")
    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO order) {
        try {
            orderService.updateOrder(order);
            return new ResponseEntity<>("order has been updated successfully", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("error while updating order " + ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Удаление ордера",
            notes = "также обновляется информация в patient")
    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>("order deleted successfully", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("error while deleting order " + ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
