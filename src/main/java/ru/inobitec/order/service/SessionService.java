package ru.inobitec.order.service;

import ru.inobitec.order.model.OrderSessionEntity;

import java.util.List;

public interface SessionService {
    OrderSessionEntity getSessionBySessionId(String sessionId);

    List<OrderSessionEntity> getAllSessions();
}
