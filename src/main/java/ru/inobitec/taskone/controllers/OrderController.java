package ru.inobitec.taskone.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inobitec.taskone.dto.OrderDTO;

import ru.inobitec.taskone.dto.OrderPatientDTO;
import ru.inobitec.taskone.http.HttpRestTempClient;
import ru.inobitec.taskone.model.Patient;
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
    public OrderPatientDTO getOrderById(@PathVariable("id") Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return new OrderPatientDTO(order, httpRestTempClient.getPatientInfoByName(order.getCustomerName()));
    }

    @PostMapping("/order")
    public String addOrder(@RequestBody OrderDTO newOrder) {
        if (httpRestTempClient.getPatientInfoByName(newOrder.getCustomerName()) == null) {
            httpRestTempClient.addNewPatient(newOrder);
        }
        httpRestTempClient.getPatientInfoByName(newOrder.getCustomerName());
        orderService.addOrder(newOrder);
        return "new order was created successfully";
    }

    @PutMapping("/updateOrder/{id}")
    public String updateOrder(@RequestBody OrderDTO orderUpdate,
                                              @PathVariable("id") Long id) {
        Patient patient = httpRestTempClient.getPatientInfoByName(orderUpdate.getCustomerName());
        if (!(patient.getLastName().equals(orderUpdate.getCustomerName()) && patient.getPhone().equals(orderUpdate.getCustomerPhone()))){
            patient.setLastName(orderUpdate.getCustomerName());
            patient.setPhone(orderUpdate.getCustomerPhone());
            httpRestTempClient.updatePatient(patient);
        }
        orderService.updateOrder(orderUpdate, id);
        return "order has been updated successfully";
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
