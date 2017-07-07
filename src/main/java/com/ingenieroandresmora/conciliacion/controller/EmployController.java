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

import com.ingenieroandresmora.conciliacion.model.Employ;
import com.ingenieroandresmora.conciliacion.model.State;
import com.ingenieroandresmora.conciliacion.service.EmployService;
import com.ingenieroandresmora.conciliacion.service.StateService;
import com.ingenieroandresmora.conciliacion.util.CustomErrorType;
import com.ingenieroandresmora.conciliacion.util.FunctionLibrary;

@Controller
@CrossOrigin
@RequestMapping(value = "/v1")
public class EmployController {

	@Autowired
	private EmployService _employService;

	@Autowired
	private StateService _stateService;

	// SHOW USERS BY EMAIL,BY IDENTIFICATION OR ALL
	@RequestMapping(value = "/employ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Employ>> getEmploys(
			@RequestParam(value = "employEmail", required = false) String employEmail,
			@RequestParam(value = "employIdentification", required = false) String employIdentification) {

		List<Employ> employs = new ArrayList<Employ>();
		if (employEmail != null) {
			Employ employ = _employService.findByEmail(employEmail);
			if (employ == null) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			employs.add(employ);
		}

		if (employIdentification != null) {
			Employ employ = _employService.findByIdentification(employIdentification);
			if (employs == null) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			employs.add(employ);
		}

		if (employEmail == null && employIdentification == null) {
			employs = (List<Employ>) _employService.findAllEmploys();
			if (employs.isEmpty()) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<List<Employ>>(employs, HttpStatus.OK);
	}

	// GET BY ID
	@RequestMapping(value = "/employ/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Employ> getEmployById(@PathVariable("id") Long id) {
		Employ employ = _employService.findById(id);
		if (employ == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Employ>(employ, HttpStatus.OK);
	}

	// CREATE
	@RequestMapping(value = "/employ", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createEmploy(@RequestBody Employ employ, UriComponentsBuilder ucBuilder) {
		if (employ.getEmployEmail().equals(null) || employ.getEmployIdentification().equals(null)
				|| employ.getEmployName().equals(null) || employ.getEmployLastName().equals(null)
				|| employ.getEmployPosition().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}
		if (_employService.findByIdentification(employ.getEmployIdentification()) != null) {
			return new ResponseEntity(new CustomErrorType("Unable to create. A Employ with name "
					+ employ.getEmployName() + " " + employ.getEmployLastName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		Integer stateId = 2;
		State stateSaved = _stateService.findById((long) stateId);

		if (stateSaved == null) {
			return new ResponseEntity(new CustomErrorType("the state was not found"), HttpStatus.NO_CONTENT);
		}

		employ.setEmployState(stateSaved);
		FunctionLibrary fl = new FunctionLibrary();
		employ.setEmployPass(fl.encript(employ.getEmployIdentification()));

		_employService.saveEmploy(employ);

		HttpHeaders headers = new HttpHeaders();		
		headers.setLocation(ucBuilder.path("/v1/employ/{id}").buildAndExpand(employ.getEmployId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	// UPDATE
	@RequestMapping(value = "/employ", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> updateEmploy(@RequestBody Employ employ, UriComponentsBuilder ucBuilder) {
		if (employ.getEmployId() == null || employ.getEmployEmail().equals(null)
				|| employ.getEmployIdentification().equals(null) || employ.getEmployName().equals(null)
				|| employ.getEmployLastName().equals(null) || employ.getEmployPosition().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}

		if (_employService.findByIdentification(employ.getEmployIdentification()) == null) {
			return new ResponseEntity(new CustomErrorType("The employ was not found "), HttpStatus.CONFLICT);
		}

		State stateSaved = _stateService.findById(employ.getEmployState().getStateId());

		if (stateSaved == null) {
			return new ResponseEntity(new CustomErrorType("the state was not found "), HttpStatus.CONFLICT);
		}
		FunctionLibrary fl = new FunctionLibrary();
		//encriptación de la contraseña
		employ.setEmployPass(fl.encript(employ.getEmployIdentification()));
		//se actualiza el usuario
		_employService.updateEmploy(employ);

		HttpHeaders headers = new HttpHeaders();		
		headers.setLocation(ucBuilder.path("/v1/employ/{id}").buildAndExpand(employ.getEmployId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// UPDATE ONLY PASS
		@RequestMapping(value = "/employResetPass", method = RequestMethod.PATCH, headers = "Accept=application/json")
		public ResponseEntity<?> resetPass(@RequestBody Employ employ, UriComponentsBuilder ucBuilder) {
			if (employ.getEmployId() == null ) {
				return new ResponseEntity(new CustomErrorType("there is no info to do the process"),
						HttpStatus.CONFLICT);
			}
			Long employId=employ.getEmployId();
			Employ employOld =_employService.findById(employId);
			if (employOld == null) {
				return new ResponseEntity(new CustomErrorType("The employ was not found "), HttpStatus.CONFLICT);
			}

			FunctionLibrary fl = new FunctionLibrary();
			//encriptación de la contraseña
			String pass=fl.encript(employOld.getEmployIdentification());
			
			//se actualiza el usuario
			_employService.updatePass(employId, pass);

			HttpHeaders headers = new HttpHeaders();		
			headers.setLocation(ucBuilder.path("/v1/employ/{id}").buildAndExpand(employId).toUri());

			return new ResponseEntity<String>(headers, HttpStatus.OK);
		}

	// LOGIN
	@RequestMapping(value = "/employLogin", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> loginEmploy(@RequestBody Employ employ, UriComponentsBuilder ucBuilder) {
		if (employ.getEmployEmail().equals(null) || employ.getEmployPass().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}
		FunctionLibrary fl = new FunctionLibrary();
		String encripted = fl.encript(employ.getEmployPass());
		String user = employ.getEmployEmail();
		if (_employService.login(user, encripted) == null) {
			return new ResponseEntity(new CustomErrorType("Do not find anybody with the data provided "),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(new CustomErrorType("logued "), HttpStatus.CONFLICT);
		}
	}

}
