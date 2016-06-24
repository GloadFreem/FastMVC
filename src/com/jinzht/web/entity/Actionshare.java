package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Actionshare entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "actionshare", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"action","users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Actionshare implements java.io.Serializable {

	// Fields

	private Integer shareId;
	private Action action;
	private Users users;
	private String content;

	// Constructors

	/** default constructor */
	public Actionshare() {
	}


	/** full constructor */
	public Actionshare(Action action, Users users, String content) {
		this.action = action;
		this.users = users;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "share_id", unique = true, nullable = false)
	public Integer getShareId() {
		return this.shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_id")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "content", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}