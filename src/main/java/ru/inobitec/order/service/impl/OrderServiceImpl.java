package ru.inobitec.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.Patient;
import ru.inobitec.order.model.OrderEntity;
import ru.inobitec.order.service.PatientService;
import ru.inobitec.order.repository.OrderMapper;
import ru.inobitec.order.service.OrderService;

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
    public OrderDTO getOrderById(Long id) {
        OrderDTO order = orderMapper.getOrderById(id).toDTO();
        Patient patient = patientService.getPatientById(order.getPatientId());
        order.setPatient(patient);
        return order;
    }

    @Override
    public void addOrder(OrderDTO order) {
        Long patientId = null;
        if (patientService.getPatientByName(order.getFirstName(), order.getLastName(), order.getBirthday()) == null) {
            patientId = patientService.addPatient(order);
        }
        order.setPatientId(patientId);
        OrderEntity orderEntity = order.toEntity();

        orderMapper.addOrder(orderEntity);
        orderMapper.addOrderItems(order.getOrderItems(), orderEntity.getId());
    }

    @Override
    public void updateOrder(OrderDTO order, Long id) {
        OrderEntity orderEntity = orderMapper.getOrderById(id);

        Patient patient = patientService.getPatientById(orderEntity.getPatientId());
        patient.setPhone(order.getCustomerPhone());
        patientService.updatePatient(patient);
        orderMapper.updateOrder(order.toEntity(), id);
        orderMapper.updateOrderItems(order.getOrderItems(), id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }
}
