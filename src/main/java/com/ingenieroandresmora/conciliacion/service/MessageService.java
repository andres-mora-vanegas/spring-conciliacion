package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Message;
import com.ingenieroandresmora.conciliacion.model.State;

public interface MessageService {

	void saveMessage(Message message);

	void deleteMessageById(Long idMessage);

	void updateMessage(Message message);

	List<Message> findAllMessages();

	Message findById(Long idMessage);

	List<Message> findByEmail(String email);
	
	List<Message> findByState(State state);
}
