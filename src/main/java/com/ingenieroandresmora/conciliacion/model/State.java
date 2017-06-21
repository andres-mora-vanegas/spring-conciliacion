package com.ingenieroandresmora.conciliacion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_state")
public class State implements Serializable {

	@Id
	@Column(name="sta_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stateId;
	
	@Column(name="sta_name")
	private String stateName;
	
	@Column(name="sta_category_id")
	private Long stateCategory;

	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	public State(String stateName, Long stateCategory) {
		super();
		this.stateName = stateName;
		this.stateCategory = stateCategory;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getStateCategory() {
		return stateCategory;
	}

	public void setStateCategory(Long stateCategory) {
		this.stateCategory = stateCategory;
	}
	
	
}
