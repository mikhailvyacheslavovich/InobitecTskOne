package ru.inobitec.taskone.filters;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.inobitec.taskone.model.OrderSessionEntity;
import ru.inobitec.taskone.service.SessionService;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
@RequiredArgsConstructor
public class SessionCache {
    @Autowired
    SessionService sessionService;

    private Map<String, OrderSessionEntity> sessions;

    @Scheduled(fixedDelay = 5000)
    public void updateCache() {
        sessions = sessionService.getAllSessions()
                .stream().collect(Collectors.toMap(OrderSessionEntity::getSessionId, x -> x));
        System.out.println(sessions.entrySet());
    }
}
