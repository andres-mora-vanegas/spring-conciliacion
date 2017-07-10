package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ingenieroandresmora.conciliacion.model.Client;
import com.ingenieroandresmora.conciliacion.model.Employ;

@Repository
@Transactional
public class ClientDaoImpl extends AbstractSession implements ClientDao {

	@Override
	public void saveClient(Client client) {
		getSession().persist(client);
	}

	@Override
	public void deleteClientById(Long idClient) {
		Client client = findById(idClient);
		if (client != null) {
			getSession().delete(client);
		}		
	}

	@Override
	public void updateClient(Client client) {
		getSession().update(client);
		
	}

	@Override
	public List<Client> findAllClients() {
		return getSession().createQuery("from Client").list();
	}

	@Override
	public Client findById(Long idClient) {
		return (Client) getSession().createQuery("from Client tc where tc.clientId=:idClient").setParameter("idClient", idClient);
	}

	@Override
	public Client login(String email, String pass) {
		return (Client) getSession().createQuery("from Client where clientEmail = :email and clientPass= :pass")
				.setParameter("email",email)
				.setParameter("pass", pass)
				.uniqueResult();
	}

	@Override
	public Client findByEmail(String email) {
		return (Client) getSession().createQuery("from Client tc where tc.clientEmail=:email").setParameter("email", email).uniqueResult();
	}
	

}
