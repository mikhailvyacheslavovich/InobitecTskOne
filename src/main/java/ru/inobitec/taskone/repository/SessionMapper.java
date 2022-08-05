package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.Mapper;
import ru.inobitec.taskone.model.OrderSessionEntity;

@Mapper
public interface SessionMapper {

    OrderSessionEntity getSessionBySessionId(String sessionId);

}
