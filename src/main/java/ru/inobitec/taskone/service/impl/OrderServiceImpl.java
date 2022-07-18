package ru.inobitec.taskone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.dto.OrderPatientDTO;
import ru.inobitec.taskone.exceptions.ResourceNotFoundException;
import ru.inobitec.taskone.http.HttpRestTempClient;
import ru.inobitec.taskone.model.Patient;
import ru.inobitec.taskone.repository.OrderMapper;
import ru.inobitec.taskone.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final HttpRestTempClient httpRestTempClient;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.getAllOrders();
    }
    @Override
    public OrderPatientDTO getOrderById(Long id) {
        OrderDTO order = orderMapper.getOrderById(id);
        return new OrderPatientDTO(order, httpRestTempClient.getPatientInfoByName(order.getOrder()));
    }

    @Override
    public void addOrder(OrderDTO newOrder) {
        if (httpRestTempClient.getPatientInfoByName(newOrder.getOrder()) == null) {
            httpRestTempClient.addNewPatient(newOrder.getOrder());
        }
        httpRestTempClient.getPatientInfoByName(newOrder.getOrder());
        orderMapper.addOrder(newOrder);
        orderMapper.addOrderItems(newOrder.getOrderItems(), newOrder.getOrder().getId());
    }

    @Override
    public void updateOrder(OrderDTO orderUpdate, Long id) {
        Patient patient = httpRestTempClient.getPatientInfoByName(orderUpdate.getOrder());
        if (!(orderUpdate.orderPatientEquals(patient))){
            patient.setLastName(orderUpdate.getOrder().getCustomerLastName());
            patient.setFirstName(orderUpdate.getOrder().getCustomerFirstName());
            patient.setBirthday(orderUpdate.getOrder().getCustomerBirthday());
            httpRestTempClient.updatePatient(patient);
        }
        orderMapper.updateOrder(orderUpdate, id);
        orderMapper.updateOrderItems(orderUpdate.getOrderItems(), id);
    }


    @Override
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderById(id);
    }
}
