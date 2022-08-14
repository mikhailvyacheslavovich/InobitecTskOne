package ru.inobitec.order.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.order.dto.OrderDTO;

import ru.inobitec.order.service.OrderService;

import static ru.inobitec.order.util.StringConstants.*;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderService orderService;

    @ApiOperation(value = "Получение сведений об ордере",
            notes = "Получить сведения об ордере по его id")
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity(ORDER_READ_NEGATIVE + id + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Создание нового ордера",
            notes = "Создается ордер здесь и в patient")
    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@RequestBody OrderDTO order) {
        try {
            orderService.addOrder(order);
            return new ResponseEntity<>(ORDER_CREATED_POSITIVE, HttpStatus.OK);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(ORDER_CREATED_NEGATIVE + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Изменение ордера",
            notes = "также обновляется информация в patient")
    @PutMapping("/order")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO order) {
        try {
            orderService.updateOrder(order);
            return new ResponseEntity<>(ORDER_UPDATED_POSITIVE, HttpStatus.OK);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(ORDER_UPDATED_NEGATIVE + order.getId() +  ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Удаление ордера",
            notes = "также обновляется информация в patient")
    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(ORDER_DELETED_POSITIVE, HttpStatus.OK);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(ORDER_DELETED_NEGATIVE + id + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
