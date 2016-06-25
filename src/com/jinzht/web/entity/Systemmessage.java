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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Systemmessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "systemmessage", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Systemmessage implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private Users users;
	private String title;
	private Messagetype messagetype;
	private String content;
	private String url;
	private Date messageDate;
	private short isRead;

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

	@ManyToOne(fetch = FetchType.EAGER)
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
	
	@Column(name = "is_read")
	public short isRead() {
		return isRead;
	}

	public void setRead(short isRead) {
		this.isRead = isRead;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}