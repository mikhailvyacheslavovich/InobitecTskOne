package ru.inobitec.order.service;

import ru.inobitec.order.model.OrderSessionEntity;

import java.util.List;

public interface SessionService {
    List<OrderSessionEntity> getAllSessions();
}
