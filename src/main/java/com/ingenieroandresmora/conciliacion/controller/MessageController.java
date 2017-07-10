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
import com.ingenieroandresmora.conciliacion.model.Message;
import com.ingenieroandresmora.conciliacion.model.State;
import com.ingenieroandresmora.conciliacion.service.MessageService;
import com.ingenieroandresmora.conciliacion.service.StateService;
import com.ingenieroandresmora.conciliacion.util.CustomErrorType;
import com.ingenieroandresmora.conciliacion.util.FunctionLibrary;

@Controller
@CrossOrigin
@RequestMapping(value = "/v1")
public class MessageController {

	@Autowired
	private StateService _stateService;

	@Autowired
	private MessageService _messageService;

	// SHOW MESSAGES BY EMAIL,BY STATE OR ALL
	@RequestMapping(value = "/message", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Message>> getMessages(
			@RequestParam(value = "messageEmail", required = false) String messageEmail,
			@RequestParam(value = "messageState", required = false) Long messageState) {
		List<Message> messages = new ArrayList<Message>();
		if (messageEmail != null) {
			messages = (List<Message>) _messageService.findByEmail(messageEmail);
			if (messages.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}
		if (messageState != null) {
			State mesState = _stateService.findById(messageState);
			messages = (List<Message>) _messageService.findByState(mesState);
			if (messages.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}
		if (messageEmail == null && messageState == null) {
			messages = (List<Message>) _messageService.findAllMessages();
			if (messages.isEmpty()) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	// GET BY ID
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id) {
		Message message = _messageService.findById(id);
		if (message == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	// CREATE
	@RequestMapping(value = "/message", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createMessage(@RequestBody Message message, UriComponentsBuilder ucBuilder) {
		if (message.getMessageName().equals(null) || message.getMessageEmail().equals(null)
				|| message.getMessagePhone().equals(null) || message.getMessageMessage().equals(null)
				|| message.getMessageCategory().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}
		Integer stateId = 1;
		State stateSaved = _stateService.findById((long) stateId);

		if (stateSaved == null) {
			return new ResponseEntity(new CustomErrorType("the state was not found"), HttpStatus.NO_CONTENT);
		}

		message.setMessageState(stateSaved);

		_messageService.saveMessage(message);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/message/{id}").buildAndExpand(message.getMessageId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	// UPDATE
	@RequestMapping(value = "/message", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> updateMessage(@RequestBody Message message, UriComponentsBuilder ucBuilder) {
		if (message.getMessageId().equals(null) || message.getMessageName().equals(null)
				|| message.getMessageEmail().equals(null) || message.getMessagePhone().equals(null)
				|| message.getMessageMessage().equals(null) || message.getMessageCategory().equals(null)
				|| message.getMessageState().equals(null)) {
			return new ResponseEntity(new CustomErrorType("one of the data needed is not present"),
					HttpStatus.CONFLICT);
		}

		if (_messageService.findById(message.getMessageId()) == null) {
			return new ResponseEntity(new CustomErrorType("The message was not found "), HttpStatus.CONFLICT);
		}

		State stateSaved = _stateService.findById(message.getMessageState().getStateId());

		if (stateSaved == null) {
			return new ResponseEntity(new CustomErrorType("the state was not found "), HttpStatus.CONFLICT);
		}
		
		message.setMessageState(stateSaved);
		// se actualiza el usuario
		_messageService.updateMessage(message);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/message/{id}").buildAndExpand(message.getMessageId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}

}
