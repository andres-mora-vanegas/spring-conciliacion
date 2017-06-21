package com.ingenieroandresmora.conciliacion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.ingenieroandresmora.conciliacion.model.*;
import com.ingenieroandresmora.conciliacion.service.*;
import com.ingenieroandresmora.conciliacion.util.*;

@Controller
@RequestMapping(value = "/v1")
public class StateController {

	@Autowired
	private StateService _stateService;

	@RequestMapping(value = "/state", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<State>> getStates(@RequestParam(value = "stateName", required = false) String stateName,
			@RequestParam(value = "stateCategory", required = false) Long stateCategory) {
		List<State> states = new ArrayList<State>();
		if (stateCategory != null) {
			states = _stateService.findByCategory(stateCategory);
			if (states.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}
		if (stateName != null) {
			State state = _stateService.findByName(stateName);
			if (state == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			states.add(state);
		}
		if (stateName == null && stateCategory == null) {
			states = _stateService.findAllStates();
			if (states.isEmpty()) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<List<State>>(states, HttpStatus.OK);

	}

	// GET BY ID
	@RequestMapping(value = "/states/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<State> getStateById(@PathVariable("id") Long id) {
		State state = _stateService.findById(id);
		if (state == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<State>(state, HttpStatus.OK);
	}

	// CREATE
	@RequestMapping(value = "/states", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createState(@RequestBody State state, UriComponentsBuilder ucBuilder) {
		if (state.getStateName().equals(null) || state.getStateCategory().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}
		if (_stateService.findByName(state.getStateName()) != null) {
			// logger.error("Unable to create. A User with name {} already
			// exist", user.getName());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A State with name " + state.getStateName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		_stateService.saveState(state);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/states/{id}").buildAndExpand(state.getStateId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// UPDATE
	@RequestMapping(value = "/state/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> updateState(@PathVariable("id") Long id, @RequestBody State state) {
		State currentState = _stateService.findById(id);

		if (currentState == null) {
			return new ResponseEntity(new CustomErrorType("Unable to update. state with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentState.setStateName(state.getStateName());
		currentState.setStateCategory(state.getStateCategory());

		_stateService.updateState(currentState);
		return new ResponseEntity<State>(currentState, HttpStatus.OK);
	}

	// DELETE
	@RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteState(@PathVariable("id") Long idState) {
		if (idState == null || idState <= 0) {
			return new ResponseEntity(new CustomErrorType("id state is required"), HttpStatus.CONFLICT);
		}

		State state = _stateService.findById(idState);
		if (state == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		_stateService.deleteStateById(idState);
		return new ResponseEntity<State>(HttpStatus.OK);

	}
}
