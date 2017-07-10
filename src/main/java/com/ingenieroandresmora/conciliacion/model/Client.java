package com.ingenieroandresmora.conciliacion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tb_clients")
public class Client implements Serializable {

	@Id
	@Column(name="cli_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long clientId;
	
	@Column(name="cli_name")
	private String clientName;
	
	@Column(name="cli_lastName")
	private String clientLastName;	
	
	@Column(name="cli_email")
	private String clientEmail;
	
	@Column(name="cli_phone")
	private String clientPhone;
	
	@Column(name="cli_pass")
	private String clientPass;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cli_date")
	private Date clientDate;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cli_state")
	private State clientState;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String clientName, String clientLastName, String clientEmail, String clientPhone, String clientPass,
			State clientState) {
		super();
		this.clientName = clientName;
		this.clientLastName = clientLastName;
		this.clientEmail = clientEmail;
		this.clientPhone = clientPhone;
		this.clientPass = clientPass;
		this.clientState = clientState;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientPass() {
		return clientPass;
	}

	public void setClientPass(String clientPass) {
		this.clientPass = clientPass;
	}

	public Date getClientDate() {
		return clientDate;
	}

	public void setClientDate(Date clientDate) {
		this.clientDate = clientDate;
	}

	public State getClientState() {
		return clientState;
	}

	public void setClientState(State clientState) {
		this.clientState = clientState;
	}
	
	
	
}
