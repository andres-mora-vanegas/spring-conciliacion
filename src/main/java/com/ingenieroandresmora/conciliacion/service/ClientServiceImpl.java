package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieroandresmora.conciliacion.dao.ClientDao;
import com.ingenieroandresmora.conciliacion.model.Client;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	

	@Autowired
	private ClientDao _clientDao;
	
	@Override
	public void saveClient(Client Client) {
		_clientDao.saveClient(Client);
	}

	@Override
	public void deleteClientById(Long idClient) { 
		_clientDao.deleteClientById(idClient);
	}

	@Override
	public void updateClient(Client Client) {
		_clientDao.updateClient(Client);		
	}

	@Override
	public List<Client> findAllClients() {
		return _clientDao.findAllClients();
	}

	@Override
	public Client findById(Long idClient) {
		return _clientDao.findById(idClient);
	}

	@Override
	public Client findByEmail(String email) {
		return _clientDao.findByEmail(email);
	}

	@Override
	public Client login(String email, String pass) {
		return _clientDao.login(email,pass);		
	}
}
