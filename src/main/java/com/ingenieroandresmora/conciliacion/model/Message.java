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
@Table(name = "tb_messages")
public class Message implements Serializable {

	@Id
	@Column(name = "mes_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;

	@Column(name = "mes_name")
	private String messageName;

	@Column(name = "mes_email")
	private String messageEmail;

	@Column(name = "mes_phone")
	private String messagePhone;

	@Column(name = "mes_message")
	private String messageMessage;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mes_date")
	private Date messageDate;

	/*
	 * @ManyToOne(optional=false, fetch=FetchType.EAGER)
	 * 
	 * @JoinColumn(name="mes_category")
	 */
	@Column(name = "mes_category")
	private Long messageCategory;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "mes_state")
	private State messageState;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String messageName, String messageEmail, String messagePhone, String messageMessage,
			Date messageDate, Long messageCategory, State messageState) {
		super();
		this.messageName = messageName;
		this.messageEmail = messageEmail;
		this.messagePhone = messagePhone;
		this.messageMessage = messageMessage;
		this.messageDate = messageDate;
		this.messageCategory = messageCategory;
		this.messageState = messageState;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getMessageEmail() {
		return messageEmail;
	}

	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}

	public String getMessagePhone() {
		return messagePhone;
	}

	public void setMessagePhone(String messagePhone) {
		this.messagePhone = messagePhone;
	}

	public String getMessageMessage() {
		return messageMessage;
	}

	public void setMessageMessage(String messageMessage) {
		this.messageMessage = messageMessage;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public Long getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(Long messageCategory) {
		this.messageCategory = messageCategory;
	}

	public State getMessageState() {
		return messageState;
	}

	public void setMessageState(State messageState) {
		this.messageState = messageState;
	}
}
