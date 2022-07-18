package ru.inobitec.taskone.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;

import ru.inobitec.taskone.dto.OrderPatient;
import ru.inobitec.taskone.http.HttpRestTempClient;
import ru.inobitec.taskone.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class OrderController {

    private final HttpRestTempClient httpRestTempClient;
    private final OrderService orderService;


    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public OrderPatient getOrderById(@PathVariable("id") Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return new OrderPatient(order, httpRestTempClient.getPatientInfoByName(order.getCustomerName()));
    }

    @PostMapping("/order")
    public String addOrder(@RequestBody OrderDTO newOrder) {
        if (httpRestTempClient.getPatientInfoByName(newOrder.getCustomerName()) == null){
            httpRestTempClient.addNewPatient(newOrder);
        }
        httpRestTempClient.getPatientInfoByName(newOrder.getCustomerName());
        orderService.addOrder(newOrder);
        return "new order was created successfully";
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderUpdate,
                                              @PathVariable("id") Long id) {
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
