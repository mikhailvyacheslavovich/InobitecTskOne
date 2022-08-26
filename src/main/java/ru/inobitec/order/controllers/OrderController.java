package ru.inobitec.order.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.order.dto.OrderDTO;

import ru.inobitec.order.service.OrderService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private static final String ORDER_READ_NEGATIVE = "Unable to get order by id ";
    private static final String ORDER_CREATED_POSITIVE = "Order was created successfully ";
    private static final String ORDER_CREATED_NEGATIVE = "Unable to update order ";
    private static final String ORDER_UPDATED_POSITIVE = "Order has been updated successfully ";
    private static final String ORDER_UPDATED_NEGATIVE = "Unable to update order by id ";
    private static final String ORDER_DELETED_POSITIVE = "Order deleted successfully ";
    private static final String ORDER_DELETED_NEGATIVE = "Unable to delete order by id ";
    private static final String NOT_EXIST_ORDER = "Can not find order with id = ";
    private static final String NOT_EXIST_ORDER_FOR_UPDATE = "Can not find order for update with id = ";

    private static final String NOT_EXIST_ORDER_FOR_DELETE = "Can not find order with id = ";

    private final OrderService orderService;

    @ApiOperation(value = "Получение сведений об ордере",
            notes = "Получить сведения об ордере по его id")
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(id);
            if (orderDTO == null) {
                return new ResponseEntity(NOT_EXIST_ORDER + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
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
    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO order, @PathVariable("id") Long id) {
        try {
            Long returnedId = orderService.updateOrder(order);
            if (returnedId == null) {
                return new ResponseEntity<>(NOT_EXIST_ORDER_FOR_UPDATE + order.getId(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ORDER_UPDATED_POSITIVE, HttpStatus.OK);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(ORDER_UPDATED_NEGATIVE + order.getId() + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Удаление ордера",
            notes = "также обновляется информация в patient")
    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id) {
        try {
            Long oId = orderService.deleteOrderById(id);
            if (oId == 0) {
                return new ResponseEntity<>(NOT_EXIST_ORDER_FOR_DELETE + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ORDER_DELETED_POSITIVE, HttpStatus.OK);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(ORDER_DELETED_NEGATIVE + id + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
