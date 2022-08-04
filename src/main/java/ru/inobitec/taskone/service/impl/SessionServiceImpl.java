package ru.inobitec.taskone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inobitec.taskone.model.Session;
import ru.inobitec.taskone.repository.OrderMapper;
import ru.inobitec.taskone.repository.SessionMapper;
import ru.inobitec.taskone.service.SessionService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    @Override
    public Session getSessionBySessionId(String sessionId) {
        return sessionMapper.getSessionBySessionId(sessionId);
    }
}
