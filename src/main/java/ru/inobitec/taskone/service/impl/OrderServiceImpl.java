package ru.inobitec.taskone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.dto.OrderPatientDTO;
import ru.inobitec.taskone.service.PatientService;
import ru.inobitec.taskone.model.Patient;
import ru.inobitec.taskone.repository.OrderMapper;
import ru.inobitec.taskone.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final PatientService patientService;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.getAllOrders();
    }
    @Override
    public OrderPatientDTO getOrderById(Long id) {
        OrderDTO order = orderMapper.getOrderById(id);
        //Patient cc = restClient.getPatientInfoByName(order.getOrder());
        //return new OrderPatientDTO(order, restClient.getPatientInfoByName(order.getOrder()));
        return new OrderPatientDTO(order, new Patient());
    }

    @Override
    public void addOrder(OrderDTO newOrder) {
        if (patientService.getPatientInfoByName(newOrder.getOrder()) == null) {
            patientService.addNewPatient(newOrder.getOrder());
        }
        patientService.getPatientInfoByName(newOrder.getOrder());
        orderMapper.addOrder(newOrder.getOrder());
        orderMapper.addOrderItems(newOrder.getOrderItems(), newOrder.getOrder().getId());
    }

    @Override
    public void updateOrder(OrderDTO orderUpdate, Long id) {
        /*
        Patient patient = restClient.getPatientInfoByName(orderUpdate.getOrder());
        if (!(orderUpdate.orderPatientEquals(patient))){
            patient.setPhone(orderUpdate.getOrder().getCustomerPhone());
            restClient.updatePatient(patient);
        }
        */

        orderMapper.updateOrder(orderUpdate.getOrder(), id);
        orderMapper.updateOrderItems(orderUpdate.getOrderItems(), id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }
}
