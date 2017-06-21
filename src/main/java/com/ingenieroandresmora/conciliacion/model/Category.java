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

public class Category implements Serializable {

	@Id
	@Column(name="cat_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long categoryId;
	
	@Column(name="cat_name")
	private String categoryName;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cat_date")
	private String categoryDate;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="sta_id")
	private State categoryState;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String categoryName, String categoryDate,State categoryState) {
		super();
		this.categoryName = categoryName;
		this.categoryDate = categoryDate;
		this.categoryState = categoryState;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDate() {
		return categoryDate;
	}

	public void setCategoryDate(String categoryDate) {
		this.categoryDate = categoryDate;
	}

	public State getCategoryState() {
		return categoryState;
	}

	public void setCategoryState(State categoryState) {
		this.categoryState = categoryState;
	}
	
	
}
