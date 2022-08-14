package ru.inobitec.order.filters;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.inobitec.order.model.OrderSessionEntity;
import ru.inobitec.order.service.SessionService;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
@RequiredArgsConstructor
@Log4j2
public class SessionCache {
    private final SessionService sessionService;

    private Map<String, OrderSessionEntity> sessions;

    @Scheduled(fixedDelay = 5000)
    public void updateCache() {
        try {
            sessions = sessionService.getAllSessions()
                    .stream().collect(Collectors.toMap(OrderSessionEntity::getSessionId, x -> x));
        } catch (RuntimeException ex) {
            log.error(ex.getCause());
            throw new RuntimeException(ex);
        }
    }
}
