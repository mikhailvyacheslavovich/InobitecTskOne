package ru.inobitec.taskone.service;

import ru.inobitec.taskone.model.Session;

import java.util.Date;
import java.util.List;

public interface SessionService {
    Session getSessionBySessionId(String sessionId);
}
