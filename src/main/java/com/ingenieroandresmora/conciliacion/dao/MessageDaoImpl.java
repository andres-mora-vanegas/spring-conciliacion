package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ingenieroandresmora.conciliacion.model.Message;
import com.ingenieroandresmora.conciliacion.model.State;

@Repository
@Transactional
public class MessageDaoImpl extends AbstractSession implements MessageDao {

	@Override
	public void saveMessage(Message message) {
		getSession().persist(message);
	}

	@Override
	public void deleteMessageById(Long idMessage) {
		Message message = findById(idMessage);
		if (message  != null) {
			getSession().delete(message);
		}		
	}

	@Override
	public void updateMessage(Message message) {
		getSession().update(message);
	}

	@Override
	public List<Message> findAllMessages() {
		return getSession().createQuery("from Message").list();
	}

	@Override
	public Message findById(Long idMessage) {
		return (Message) getSession().get(Message.class, idMessage);
	}

	@Override
	public List<Message> findByEmail(String email) {
		return getSession().createQuery(
				"from Message where messageEmail = :email")
				.setParameter("email", email).list();
	}

	@Override
	public List<Message> findByState(State state) {
		return  getSession().createQuery(
				"from Message where messageState = :state")
				.setParameter("state", state).list();
	}
}
