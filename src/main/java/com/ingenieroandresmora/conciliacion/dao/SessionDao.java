package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Session;

public interface SessionDao {
	void saveSession(Session Session);

	void deleteSessionById(Long idSession);

	void updateSession(Session Session);

	List<Session> findAllSessions();

	Session findById(Long idSession);

	Session findByName(String name);
}
