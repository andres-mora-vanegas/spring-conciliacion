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

public class CaseAssign implements Serializable {

	@Id
	@Column(name="ca_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long caseAssignId;		
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="mes_id")
	private Message caseAssignCaseId;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="emp_id")
	private Employ caseAssignEmployId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ca_date")
	private Date caseAssignDate;	
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State caseAssignState;

	public CaseAssign() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CaseAssign(Message caseAssignCaseId, Employ caseAssignEmployId, Date caseAssignDate,
			State caseAssignState) {
		super();
		this.caseAssignCaseId = caseAssignCaseId;
		this.caseAssignEmployId = caseAssignEmployId;
		this.caseAssignDate = caseAssignDate;
		this.caseAssignState = caseAssignState;
	}

	public Long getCaseAssignId() {
		return caseAssignId;
	}

	public void setCaseAssignId(Long caseAssignId) {
		this.caseAssignId = caseAssignId;
	}

	public Message getCaseAssignCaseId() {
		return caseAssignCaseId;
	}

	public void setCaseAssignCaseId(Message caseAssignCaseId) {
		this.caseAssignCaseId = caseAssignCaseId;
	}

	public Employ getCaseAssignEmployId() {
		return caseAssignEmployId;
	}

	public void setCaseAssignEmployId(Employ caseAssignEmployId) {
		this.caseAssignEmployId = caseAssignEmployId;
	}

	public Date getCaseAssignDate() {
		return caseAssignDate;
	}

	public void setCaseAssignDate(Date caseAssignDate) {
		this.caseAssignDate = caseAssignDate;
	}

	public State getCaseAssignState() {
		return caseAssignState;
	}

	public void setCaseAssignState(State caseAssignState) {
		this.caseAssignState = caseAssignState;
	}
	
	
}
