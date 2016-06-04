package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Actioncomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "actioncomment", catalog = "jinzht2016")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"action"})
public class Actioncomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Users usersByUserId;
	private Users usersByAtUserId;
	private Action action;
	private String content;
	private String userName;
	private String atUserName;

	// Constructors

	/** default constructor */
	public Actioncomment() {
	}

	/** full constructor */
	public Actioncomment(Users usersByUserId, Users usersByAtUserId, Action action, String content) {
		this.usersByUserId = usersByUserId;
		this.usersByAtUserId = usersByAtUserId;
		this.action = action;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsersByUserId() {
		return this.usersByUserId;
	}

	public void setUsersByUserId(Users usersByUserId) {
		this.usersByUserId = usersByUserId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "at_user_id")
	public Users getUsersByAtUserId() {
		return this.usersByAtUserId;
	}

	public void setUsersByAtUserId(Users usersByAtUserId) {
		this.usersByAtUserId = usersByAtUserId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "action_id")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAtUserName() {
		return atUserName;
	}

	public void setAtUserName(String atUserName) {
		this.atUserName = atUserName;
	}

}