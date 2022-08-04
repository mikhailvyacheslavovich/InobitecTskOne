package ru.inobitec.taskone.repository;

import org.apache.ibatis.annotations.Mapper;
import ru.inobitec.taskone.model.Session;

@Mapper
public interface SessionMapper {

    Session getSessionBySessionId(String sessionId);

}
