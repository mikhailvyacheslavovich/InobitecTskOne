package ru.inobitec.taskone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.inobitec.taskone.dto.OrderDTO;
import ru.inobitec.taskone.dto.OrderPatientDTO;
import ru.inobitec.taskone.service.PatientService;
import ru.inobitec.taskone.model.PatientEntity;
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
        //PatientEntity cc = patientService.getPatientInfoByName(order.getOrderEntity());
        return new OrderPatientDTO(order, patientService.getPatientInfoByName(order.getOrderEntity()));
    }

    @Override
    public void addOrder(OrderDTO newOrder) {

        if (patientService.getPatientInfoByName(newOrder.getOrderEntity()) == null) {
            patientService.addNewPatient(newOrder.getOrderEntity());
        }
        patientService.getPatientInfoByName(newOrder.getOrderEntity());
        orderMapper.addOrder(newOrder.getOrderEntity());
        orderMapper.addOrderItems(newOrder.getOrderItems(), newOrder.getOrderEntity().getId());

    }

    @Override
    public void updateOrder(OrderDTO orderUpdate, Long id) {
        /*
        PatientEntity patient = patientService.getPatientInfoByName(orderUpdate.getOrderEntity());
        if (!(orderUpdate.orderPatientEquals(patient))) {
            patient.setPhone(orderUpdate.getOrderEntity().getCustomerPhone());
            patientService.updatePatient(patient);
        }
        */
        orderMapper.updateOrder(orderUpdate.getOrderEntity(), id);
        orderMapper.updateOrderItems(orderUpdate.getOrderItems(), id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderMapper.deleteOrderItemsById(id);
        orderMapper.deleteOrderById(id);
    }
}
