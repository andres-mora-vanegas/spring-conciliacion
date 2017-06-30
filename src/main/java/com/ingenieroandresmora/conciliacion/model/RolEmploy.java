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
@Table(name="tb_rol_employ")
public class RolEmploy implements Serializable {

	@Id
	@Column(name="re_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rolEmployId;		
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="re_employ_id")
	private Employ rolEmployEmployId;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="re_rol_id")
	private Rol rolEmployRolId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="re_date")
	private Date rolEmployDate;	
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="re_state")
	private State rolEmployState;

	public RolEmploy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolEmploy(Employ rolEmployEmployId, Rol rolEmployRolId, State rolEmployState) {
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

	public Rol getRolEmployRolId() {
		return rolEmployRolId;
	}

	public void setRolEmployRolId(Rol rolEmployRolId) {
		this.rolEmployRolId = rolEmployRolId;
	}

	public Date getRolEmployDate() {
		return rolEmployDate;
	}

	public void setRolEmployDate(Date rolEmployDate) {
		this.rolEmployDate = rolEmployDate;
	}

	public State getRolEmployState() {
		return rolEmployState;
	}

	public void setRolEmployState(State rolEmployState) {
		this.rolEmployState = rolEmployState;
	}
	
	
	
}
