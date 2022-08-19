package ru.inobitec.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.Patient;
import ru.inobitec.order.rabbit.RabbitSender;
import ru.inobitec.order.repository.OrderRepository;
import ru.inobitec.order.service.PatientService;
import ru.inobitec.order.service.OrderService;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private static final String RABBIT_CREATE_COMMAND = " created";
    private static final String RABBIT_UPDATE_COMMAND = " updated";
    private final RabbitSender rabbitSender;
    private final PatientService patientService;
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO getOrderById(Long id) {
        try {
            OrderDTO order = orderRepository.getOrderById(id);
            Patient patient = patientService.getPatientById(order.getPatientId());
            order.setPatient(patient);
            return order;
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addOrder(OrderDTO order) {
        try {
            Long patientId = null;
            if (patientService.getPatientByName(order.getFirstName(), order.getLastName(), order.getMidName(), order.getBirthday()) == null) {
                patientId = patientService.addPatient(order);
            }
            order.setPatientId(patientId);
            orderRepository.addOrder(order.toEntity());
            rabbitSender.sendMessage(order.getOrderStatusId() + RABBIT_CREATE_COMMAND);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateOrder(OrderDTO order) {
        try {
            OrderDTO orderDTO = orderRepository.getOrderById(order.getId());
            Patient patient = patientService.getPatientById(orderDTO.getPatientId());
            patient.setPhone(order.getPhone());
            patient.setEmail(order.getEmail());
            patient.setAddress(order.getAddress());
            patient.setGenderId(order.getGenderId());
            patientService.updatePatient(patient);
            orderRepository.updateOrder(order.toEntity());
            rabbitSender.sendMessage(order.getOrderStatusId() + RABBIT_UPDATE_COMMAND);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        try {
            orderRepository.deleteOrderById(id);
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }
}
