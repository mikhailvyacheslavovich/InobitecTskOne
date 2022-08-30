package ru.inobitec.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import ru.inobitec.order.dto.OrderDTO;
import ru.inobitec.order.dto.Patient;
import ru.inobitec.order.exceptions.OrderEntityNotFoundException;
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
        OrderDTO order = orderRepository.getOrderById(id);
        if (order == null)
            throw new OrderEntityNotFoundException();
        Patient patient = patientService.getPatientById(order.getPatientId());
        order.setPatient(patient);
        return order;
    }

    @Override
    public void addOrder(OrderDTO order) {

        Long patientId;
        Patient patient = patientService.getPatientByName(order.getFirstName(), order.getLastName(), order.getMidName(), order.getBirthday());
        if (patient == null)
            patientId = patientService.addPatient(order);
        else
            patientId = patient.getId();
        order.setPatientId(patientId);
        orderRepository.addOrder(order.toEntity());
        rabbitSender.sendMessage(order.getOrderStatusId() + RABBIT_CREATE_COMMAND);
    }

    @Override
    public Long updateOrder(OrderDTO order) {
        OrderDTO orderDTO = orderRepository.getOrderById(order.getId());
        if (orderDTO != null) {
            Patient patient = patientService.getPatientById(orderDTO.getPatientId());
            patient.setPhone(order.getPhone());
            patient.setEmail(order.getEmail());
            patient.setAddress(order.getAddress());
            patient.setGenderId(order.getGenderId());
            patientService.updatePatient(patient);
            Long id = orderRepository.updateOrder(order.toEntity()).getId();
            rabbitSender.sendMessage(order.getOrderStatusId() + RABBIT_UPDATE_COMMAND);
            return id;
        } else {
            throw new OrderEntityNotFoundException();
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        Long quantity = orderRepository.deleteOrderById(id);
        if (quantity == 0)
            throw new OrderEntityNotFoundException();
    }
}
