package com.ingenieroandresmora.conciliacion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

public class Answer implements Serializable {

	@Id
	@Column(name="ans_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long answerId;
	
	@Column(name="ans_text")
	private String answerText;		
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="mes_id")
	private Message answerMessageId;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="emp_id")
	private Employ answerEmployId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ans_date")
	private Date answerDate;	
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State answerState;

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(String answerText, Message answerMessageId, Employ answerEmployId, Date answerDate,
			State answerState) {
		super();
		this.answerText = answerText;
		this.answerMessageId = answerMessageId;
		this.answerEmployId = answerEmployId;
		this.answerDate = answerDate;
		this.answerState = answerState;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Message getAnwerMessageId() {
		return answerMessageId;
	}

	public void setAnwerMessageId(Message answerMessageId) {
		this.answerMessageId = answerMessageId;
	}

	public Employ getAnswerEmployId() {
		return answerEmployId;
	}

	public void setAnswerEmployId(Employ answerEmployId) {
		this.answerEmployId = answerEmployId;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public State getAnswerState() {
		return answerState;
	}

	public void setAnswerState(State answerState) {
		this.answerState = answerState;
	}
	
	
}
