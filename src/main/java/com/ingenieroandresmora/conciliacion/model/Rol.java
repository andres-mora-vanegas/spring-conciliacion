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
@Table(name="tb_rol")
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
	private Date rolDate;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="rol_state")
	private State rolState;

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(String rolDescript, Date rolDate, State rolState) {
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

	public Date getRolDate() {
		return rolDate;
	}

	public void setRolDate(Date rolDate) {
		this.rolDate = rolDate;
	}

	public State getRolState() {
		return rolState;
	}

	public void setRolState(State rolState) {
		this.rolState = rolState;
	}
	
	
}
