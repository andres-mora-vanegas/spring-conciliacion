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

public class Session implements Serializable {

	@Id
	@Column(name="ses_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sessionId;		
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="ses_ca_id")
	private CaseAssign sessionCaseId;	
	
	@Column(name="ses_url")
	private String sessionUrl;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="ses_category")
	private Category sessionCategoryId;
	
	@Column(name="ses_date_initial")
	private String sessionDateInitial;
	
	@Column(name="ses_date_final")
	private String sessionDateFinal;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ses_date")
	private Date sessionDate;	
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State sessionState;

	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Session(CaseAssign sessionCaseId, String sessionUrl, Category sessionCategoryId, String sessionDateInitial,
			String sessionDateFinal, Date sessionDate, State sessionState) {
		super();
		this.sessionCaseId = sessionCaseId;
		this.sessionUrl = sessionUrl;
		this.sessionCategoryId = sessionCategoryId;
		this.sessionDateInitial = sessionDateInitial;
		this.sessionDateFinal = sessionDateFinal;
		this.sessionDate = sessionDate;
		this.sessionState = sessionState;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public CaseAssign getSessionCaseId() {
		return sessionCaseId;
	}

	public void setSessionCaseId(CaseAssign sessionCaseId) {
		this.sessionCaseId = sessionCaseId;
	}

	public String getSessionUrl() {
		return sessionUrl;
	}

	public void setSessionUrl(String sessionUrl) {
		this.sessionUrl = sessionUrl;
	}

	public Category getSessionCategoryId() {
		return sessionCategoryId;
	}

	public void setSessionCategoryId(Category sessionCategoryId) {
		this.sessionCategoryId = sessionCategoryId;
	}

	public String getSessionDateInitial() {
		return sessionDateInitial;
	}

	public void setSessionDateInitial(String sessionDateInitial) {
		this.sessionDateInitial = sessionDateInitial;
	}

	public String getSessionDateFinal() {
		return sessionDateFinal;
	}

	public void setSessionDateFinal(String sessionDateFinal) {
		this.sessionDateFinal = sessionDateFinal;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public State getSessionState() {
		return sessionState;
	}

	public void setSessionState(State sessionState) {
		this.sessionState = sessionState;
	}
	
	
}
