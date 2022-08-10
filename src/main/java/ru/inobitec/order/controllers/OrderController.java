package ru.inobitec.order.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.order.dto.OrderDTO;

import ru.inobitec.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ApiOperation(value = "Получение списка всех ордеров",
            notes = "Получить все ордера")
    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @ApiOperation(value = "Получение сведений об ордере",
            notes = "Получить сведения об ордере по его id")
    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @ApiOperation(value = "Создание нового ордера",
            notes = "Создается ордер здесь и в patient")
    @PostMapping("/order")
    public String addOrder(@RequestBody OrderDTO order) {
        orderService.addOrder(order);
        return "new order was created successfully";
    }

    @ApiOperation(value = "Изменение ордера",
            notes = "также обновляется информация в patient")
    @PutMapping("/order/{id}")
    public String updateOrder(@RequestBody OrderDTO order,
                              @PathVariable("id") Long id) {
        orderService.updateOrder(order, id);
        return "order has been updated successfully";
    }

    @ApiOperation(value = "Удаление ордера",
            notes = "также обновляется информация в patient")
    @DeleteMapping("/order/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "order deleted successfully";
    }
}
