package com.ingenieroandresmora.conciliacion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.ingenieroandresmora.conciliacion.model.Client;
import com.ingenieroandresmora.conciliacion.model.State;
import com.ingenieroandresmora.conciliacion.service.ClientService;
import com.ingenieroandresmora.conciliacion.service.StateService;
import com.ingenieroandresmora.conciliacion.util.CustomErrorType;
import com.ingenieroandresmora.conciliacion.util.FunctionLibrary;

@Controller
@CrossOrigin
@RequestMapping(value = "/v1")
public class ClientController {

	@Autowired
	private StateService _stateService;

	@Autowired
	private ClientService _clientService;

	// SHOW CLIENTS BY EMAIL,BY IDENTIFICATION OR ALL
	@RequestMapping(value = "/client", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Client>> getClients(
			@RequestParam(value = "clientEmail", required = false) String clientEmail
	/*
	 * ,@RequestParam(value = "clientIdentification", required = false) String
	 * clientIdentification
	 */
	) {

		List<Client> clients = new ArrayList<Client>();
		if (clientEmail != null) {
			Client client = _clientService.findByEmail(clientEmail);
			if (client == null) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			clients.add(client);
		}
		/*
		 * if (clientIdentification != null) { Client client =
		 * _clientService.findByIdentification(clientIdentification); if
		 * (clients == null) { return new ResponseEntity(HttpStatus.NO_CONTENT);
		 * } clients.add(client); } if (clientEmail == null &&
		 * clientIdentification == null) {
		 */
		clients = (List<Client>) _clientService.findAllClients();
		if (clients.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		/* } */
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	// GET BY ID
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
		Client client = _clientService.findById(id);
		if (client == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	// CREATE
	@RequestMapping(value = "/client", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		if (client.getClientEmail().equals(null) || client.getClientPhone().equals(null)
				|| client.getClientName().equals(null) || client.getClientLastName().equals(null)
				|| client.getClientPass().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}
		if (_clientService.findByEmail(client.getClientEmail()) != null) {
			return new ResponseEntity(new CustomErrorType("Unable to create. A Client with name "
					+ client.getClientName() + " " + client.getClientLastName() + " already exist."),
					HttpStatus.CONFLICT);
		}

		Integer stateId = 2;
		State stateSaved = _stateService.findById((long) stateId);

		if (stateSaved == null) {
			return new ResponseEntity(new CustomErrorType("the state was not found"), HttpStatus.NO_CONTENT);
		}

		client.setClientState(stateSaved);
		FunctionLibrary fl = new FunctionLibrary();
		client.setClientPass(fl.encript(client.getClientPass()));

		_clientService.saveClient(client);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/client/{id}").buildAndExpand(client.getClientId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	// UPDATE
	@RequestMapping(value = "/client", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> updateClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		if (client.getClientId() == null || client.getClientEmail().equals(null) || client.getClientName().equals(null)
				|| client.getClientLastName().equals(null) || client.getClientPhone().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}

		Client clientSaved = _clientService.findById(client.getClientId());
		if (clientSaved == null) {
			return new ResponseEntity(new CustomErrorType("The client was not found "), HttpStatus.CONFLICT);
		}

		State stateSaved = _stateService.findById(client.getClientState().getStateId());

		if (stateSaved == null) {
			return new ResponseEntity(new CustomErrorType("the state was not found "), HttpStatus.CONFLICT);
		}
		String pass = client.getClientPass();
		// si no se envió la contraseña se dejará la actual
		if (client.getClientPass().equals("") || client.getClientPass() == null) {
			pass = clientSaved.getClientPass();
		}
		FunctionLibrary fl = new FunctionLibrary();
		// encriptación de la contraseña
		client.setClientPass(fl.encript(pass));
		// se actualiza el cliente
		_clientService.updateClient(client);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/client/{id}").buildAndExpand(client.getClientId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}

	// LOGIN
	@RequestMapping(value = "/clientLogin", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> loginClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		if (client.getClientEmail().equals(null) || client.getClientPass().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}
		FunctionLibrary fl = new FunctionLibrary();
		String encripted = fl.encript(client.getClientPass());
		String user = client.getClientEmail();
		if (_clientService.login(user, encripted) == null) {
			return new ResponseEntity(new CustomErrorType("Do not find anybody with the data provided "),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(new CustomErrorType("logued "), HttpStatus.CONFLICT);
		}
	}

	/*
	 * // UPDATE ONLY PASS
	 * 
	 * @RequestMapping(value = "/clientResetPass", method = RequestMethod.PATCH,
	 * headers = "Accept=application/json") public ResponseEntity<?>
	 * resetPass(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
	 * if (client.getClientId() == null) { return new ResponseEntity(new
	 * CustomErrorType("there is no info to do the process"),
	 * HttpStatus.CONFLICT); } Long clientId = client.getClientId(); Client
	 * clientOld = _clientService.findById(clientId); if (clientOld == null) {
	 * return new ResponseEntity(new
	 * CustomErrorType("The client was not found "), HttpStatus.CONFLICT); }
	 * 
	 * FunctionLibrary fl = new FunctionLibrary(); // encriptación de la
	 * contraseña String pass = fl.encript(clientOld.getClientIdentification());
	 * 
	 * // se actualiza el usuario _clientService.updatePass(clientId, pass);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/v1/client/{id}").buildAndExpand(
	 * clientId).toUri());
	 * 
	 * return new ResponseEntity<String>(headers, HttpStatus.OK); }
	 */

}
