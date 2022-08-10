package ru.inobitec.order.mappers;

import org.apache.ibatis.annotations.Mapper;
import ru.inobitec.order.model.OrderSessionEntity;

import java.util.List;

@Mapper
public interface SessionMapper {
    OrderSessionEntity getSessionBySessionId(String sessionId);

    List<OrderSessionEntity> getAllSessions();

}
