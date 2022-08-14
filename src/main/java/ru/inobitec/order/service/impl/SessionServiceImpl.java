package ru.inobitec.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.inobitec.order.model.OrderSessionEntity;
import ru.inobitec.order.mappers.SessionMapper;
import ru.inobitec.order.service.SessionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    @Override
    public List<OrderSessionEntity> getAllSessions() {
        try {
            return sessionMapper.getAllSessions();
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }
}
