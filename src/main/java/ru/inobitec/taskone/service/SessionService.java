package ru.inobitec.taskone.service;

import ru.inobitec.taskone.model.OrderSessionEntity;

import java.util.List;

public interface SessionService {
    OrderSessionEntity getSessionBySessionId(String sessionId);

    List<OrderSessionEntity> getAllSessions();
}
