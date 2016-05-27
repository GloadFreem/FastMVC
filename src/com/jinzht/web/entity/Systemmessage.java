package com.jinzht.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Systemmessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "systemmessage", catalog = "jinzht2016")
public class Systemmessage implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private Users users;
	private Messagetype messagetype;
	private String content;
	private Date messageDate;

	// Constructors

	/** default constructor */
	public Systemmessage() {
	}

	/** full constructor */
	public Systemmessage(Users users, Messagetype messagetype, String content,
			Date messageDate) {
		this.users = users;
		this.messagetype = messagetype;
		this.content = content;
		this.messageDate = messageDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "message_id", unique = true, nullable = false)
	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_type_id")
	public Messagetype getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(Messagetype messagetype) {
		this.messagetype = messagetype;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "message_date", length = 0)
	public Date getMessageDate() {
		return this.messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

}