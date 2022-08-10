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
    public List<OrderDTO> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        OrderDTO order = orderRepository.getOrderById(id);
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
        orderRepository.addOrder(order);
    }

    @Override
    public void updateOrder(OrderDTO order, Long id) {
        OrderDTO orderDTO = orderRepository.getOrderById(id);
        Patient patient = patientService.getPatientById(orderDTO.getPatientId());
        patient.setPhone(order.getCustomerPhone());
        patientService.updatePatient(patient);
        orderRepository.updateOrder(order, id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteOrderById(id);
    }
}
