package ru.inobitec.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inobitec.order.model.OrderSessionEntity;
import ru.inobitec.order.repository.SessionMapper;
import ru.inobitec.order.service.SessionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    @Override
    public OrderSessionEntity getSessionBySessionId(String sessionId) {
        return sessionMapper.getSessionBySessionId(sessionId);
    }

    @Override
    public List<OrderSessionEntity> getAllSessions() {
        return sessionMapper.getAllSessions();
    }
}
