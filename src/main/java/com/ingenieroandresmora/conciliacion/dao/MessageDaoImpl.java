package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Message;
import com.ingenieroandresmora.conciliacion.model.State;

public class MessageDaoImpl extends AbstractSession implements MessageDao {

	@Override
	public void saveMessage(Message message) {
		// TODO Auto-generated method stub
		getSession().persist(message);
	}

	@Override
	public void deleteMessageById(Long idMessage) {
		// TODO Auto-generated method stub
		Message message = findById(idMessage);
		if (message  != null) {
			getSession().delete(message);
		}
		
	}

	@Override
	public void updateMessage(Message message) {
		// TODO Auto-generated method stub
		getSession().update(message);
	}

	@Override
	public List<Message> findAllMessages() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Message").list();
	}

	@Override
	public Message findById(Long idMessage) {
		// TODO Auto-generated method stub
		return (Message) getSession().get(Message.class, idMessage);
	}

	@Override
	public Message findByEmail(String email) {
		// TODO Auto-generated method stub
		return (Message) getSession().createQuery(
				"from Message where messageEmail = :email")
				.setParameter("email", email).list();
	}

	@Override
	public Message findByState(Long state) {
		// TODO Auto-generated method stub
		return (Message) getSession().createQuery(
				"from Message where messageState = :state")
				.setParameter("state", state).list();
	}
}
