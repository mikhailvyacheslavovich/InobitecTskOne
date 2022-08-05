package ru.inobitec.taskone.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;

import ru.inobitec.taskone.dto.OrderPatientDTO;
import ru.inobitec.taskone.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
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
    public OrderPatientDTO getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @ApiOperation(value = "Создание нового ордера",
            notes = "Создается ордер здесь и в patient")
    @PostMapping("/order")
    public String addOrder(@RequestBody OrderDTO newOrder) {
        orderService.addOrder(newOrder);
        return "new order was created successfully";
    }

    @ApiOperation(value = "Изменение ордера",
            notes = "также обновляется информация в patient")
    @PutMapping("/updateOrder/{id}")
    public String updateOrder(@RequestBody OrderDTO orderUpdate,
                              @PathVariable("id") Long id) {
        orderService.updateOrder(orderUpdate, id);
        return "order has been updated successfully";
    }

    @ApiOperation(value = "Удаление ордера",
            notes = "также обновляется информация в patient")
    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "order deleted successfully";
    }
}
