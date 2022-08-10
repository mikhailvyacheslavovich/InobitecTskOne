package ru.inobitec.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.Patient;
import ru.inobitec.order.repository.OrderRepository;
import ru.inobitec.order.service.PatientService;
import ru.inobitec.order.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PatientService patientService;

    private final OrderRepository orderRepository;

    @Override
    public OrderDTO getOrderById(Long id) throws RuntimeException {
        OrderDTO order = orderRepository.getOrderById(id);
        Patient patient = patientService.getPatientById(order.getPatientId());
        order.setPatient(patient);
        return order;
    }

    @Override
    public void addOrder(OrderDTO order) throws RuntimeException {
        Long patientId = null;
        if (patientService.getPatientByName(order.getFirstName(), order.getLastName(), order.getBirthday()) == null) {
            patientId = patientService.addPatient(order);
        }
        order.setPatientId(patientId);
        orderRepository.addOrder(order);
    }

    @Override
    public void updateOrder(OrderDTO order) throws RuntimeException {
        OrderDTO orderDTO = orderRepository.getOrderById(order.getId());
        Patient patient = patientService.getPatientById(orderDTO.getPatientId());
        patient.setPhone(order.getCustomerPhone());
        patientService.updatePatient(patient);
        orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrderById(Long id) throws RuntimeException {
        orderRepository.deleteOrderById(id);
    }
}
