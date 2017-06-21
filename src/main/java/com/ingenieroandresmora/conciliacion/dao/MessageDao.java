package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Message;

public interface MessageDao {
	void saveMessage(Message message);

	void deleteMessageById(Long idMessage);

	void updateMessage(Message message);

	List<Message> findAllMessages();

	Message findById(Long idMessage);

	Message findByEmail(String email);
	
	Message findByState(Long state);
}
