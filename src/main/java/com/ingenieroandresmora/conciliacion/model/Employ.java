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
@Table(name="tb_employ")
public class Employ implements Serializable{

	@Id
	@Column(name="emp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long employId;
	
	@Column(name="emp_name")
	private String employName;
	
	@Column(name="emp_last_name")
	private String employLastName;
	
	@Column(name="emp_identification")
	private String employIdentification;
	
	@Column(name="emp_email")
	private String employEmail;
	
	@Column(name="emp_position")
	private String employPosition;
	
	@Column(name="emp_pass")
	private String employPass;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="emp_date")
	private Date employDate;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State employState;
	
	
	public Employ() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Employ(String employName, String employLastName, String employIdentification, String employEmail,
			String employPosition, String employPass, Date employDate, State employState) {
		super();
		this.employName = employName;
		this.employLastName = employLastName;
		this.employIdentification = employIdentification;
		this.employEmail = employEmail;
		this.employPosition = employPosition;
		this.employPass = employPass;
		this.employDate = employDate;
		this.employState = employState;
	}

	public Long getEmployId() {
		return employId;
	}

	public void setEmployId(Long employId) {
		this.employId = employId;
	}

	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}

	public String getEmployLastName() {
		return employLastName;
	}

	public void setEmployLastName(String employLastName) {
		this.employLastName = employLastName;
	}

	public String getEmployIdentification() {
		return employIdentification;
	}

	public void setEmployIdentification(String employIdentification) {
		this.employIdentification = employIdentification;
	}

	public String getEmployEmail() {
		return employEmail;
	}

	public void setEmployEmail(String employEmail) {
		this.employEmail = employEmail;
	}

	public String getEmployPosition() {
		return employPosition;
	}

	public void setEmployPosition(String employPosition) {
		this.employPosition = employPosition;
	}

	public String getEmployPass() {
		return employPass;
	}

	public void setEmployPass(String employPass) {
		this.employPass = employPass;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	
	
}
