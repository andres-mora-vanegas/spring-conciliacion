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

public class RolEmploy implements Serializable {

	@Id
	@Column(name="re_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rolEmployId;		
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="emp_id")
	private Employ rolEmployEmployId;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="rol_id")
	private Employ rolEmployRolId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="re_date")
	private String rolEmployDate;	
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State rolEmployState;

	public RolEmploy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolEmploy(Employ rolEmployEmployId, Employ rolEmployRolId, State rolEmployState) {
		super();
		this.rolEmployEmployId = rolEmployEmployId;
		this.rolEmployRolId = rolEmployRolId;
		this.rolEmployState = rolEmployState;
	}

	public Long getRolEmployId() {
		return rolEmployId;
	}

	public void setRolEmployId(Long rolEmployId) {
		this.rolEmployId = rolEmployId;
	}

	public Employ getRolEmployEmployId() {
		return rolEmployEmployId;
	}

	public void setRolEmployEmployId(Employ rolEmployEmployId) {
		this.rolEmployEmployId = rolEmployEmployId;
	}

	public Employ getRolEmployRolId() {
		return rolEmployRolId;
	}

	public void setRolEmployRolId(Employ rolEmployRolId) {
		this.rolEmployRolId = rolEmployRolId;
	}

	public String getRolEmployDate() {
		return rolEmployDate;
	}

	public void setRolEmployDate(String rolEmployDate) {
		this.rolEmployDate = rolEmployDate;
	}

	public State getRolEmployState() {
		return rolEmployState;
	}

	public void setRolEmployState(State rolEmployState) {
		this.rolEmployState = rolEmployState;
	}
	
	
	
}
