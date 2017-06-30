package com.ingenieroandresmora.conciliacion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ingenieroandresmora.conciliacion.model.Employ;
import com.ingenieroandresmora.conciliacion.service.EmployService;

@Controller
@RequestMapping(value = "/v1")
public class EmployController {

	@Autowired
	private EmployService _employService;

	@RequestMapping(value = "/employ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Employ>> getEmploys(
			@RequestParam(value = "employEmail", required = false) String employEmail,
			@RequestParam(value = "employIdentification", required = false) String employIdentification) {
		List<Employ> employs = new ArrayList<Employ>();
		if (employEmail != null) {
			Employ employ = _employService.findByEmail(employEmail);
			if(employ==null){
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			employs.add(employ);
		}

		if (employIdentification != null) {
			Employ employ = _employService.findByIdentification(employIdentification);
			if(employs==null){
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

	/*
	 * @RequestMapping(value = "/employ", method = RequestMethod.GET, headers =
	 * "Accept=application/json") public ResponseEntity<List<Employ>>
	 * getEmploys(@RequestParam(value = "employEmail", required = false) String
	 * employEmail,
	 * 
	 * @RequestParam(value = "stateCategory", required = false) Long
	 * stateCategory) { List<Employ> employs = new ArrayList<Employ>(); if
	 * (stateName == null && stateCategory == null) { employs = (List<Employ>)
	 * _employService.findActiveConciliator(); if (employs.isEmpty()) { return
	 * new ResponseEntity(HttpStatus.NOT_FOUND); } } return new
	 * ResponseEntity<List<Employ>>(employs, HttpStatus.OK); }
	 */
}
