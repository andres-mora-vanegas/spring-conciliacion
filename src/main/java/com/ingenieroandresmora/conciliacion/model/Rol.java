package com.ingenieroandresmora.conciliacion.model;

import java.io.Serializable;

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

public class Rol implements Serializable  {

	@Id
	@Column(name="rol_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rolId;
	
	@Column(name="rol_descript")
	private String rolDescript;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rol_date")
	private String rolDate;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State rolState;

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(String rolDescript, String rolDate, State rolState) {
		super();
		this.rolDescript = rolDescript;
		this.rolDate = rolDate;
		this.rolState = rolState;
	}

	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getRolDescript() {
		return rolDescript;
	}

	public void setRolDescript(String rolDescript) {
		this.rolDescript = rolDescript;
	}

	public String getRolDate() {
		return rolDate;
	}

	public void setRolDate(String rolDate) {
		this.rolDate = rolDate;
	}

	public State getRolState() {
		return rolState;
	}

	public void setRolState(State rolState) {
		this.rolState = rolState;
	}
	
	
}
