package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.Mapper;
import ru.inobitec.taskone.model.OrderSessionEntity;

import java.util.List;

@Mapper
public interface SessionMapper {
    OrderSessionEntity getSessionBySessionId(String sessionId);

    List<OrderSessionEntity> getAllSessions();

}
