package ru.inobitec.taskone.service;

import ru.inobitec.taskone.model.OrderSessionEntity;

public interface SessionService {
    OrderSessionEntity getSessionBySessionId(String sessionId);
}
