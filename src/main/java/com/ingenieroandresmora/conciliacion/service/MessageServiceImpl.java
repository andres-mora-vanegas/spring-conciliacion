package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieroandresmora.conciliacion.dao.MessageDao;
import com.ingenieroandresmora.conciliacion.model.Message;
import com.ingenieroandresmora.conciliacion.model.State;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao _messageDao;
	
	@Override
	public void saveMessage(Message message) {
		_messageDao.saveMessage(message);
		
	}

	@Override
	public void deleteMessageById(Long idMessage) {
		_messageDao.deleteMessageById(idMessage);
		
	}

	@Override
	public void updateMessage(Message message) {
		_messageDao.updateMessage(message); 
		
	}

	@Override
	public List<Message> findAllMessages() {
		return _messageDao.findAllMessages();
	}

	@Override
	public Message findById(Long idMessage) {
		return _messageDao.findById(idMessage);
	}

	@Override
	public List<Message> findByEmail(String email) {
		return _messageDao.findByEmail(email);
	}

	@Override
	public List<Message> findByState(State state) { 
		return _messageDao.findByState(state);
	}

	
}
