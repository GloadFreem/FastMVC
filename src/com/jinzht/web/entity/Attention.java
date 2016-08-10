package com.jinzht.web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Attention entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attention", catalog = "jinzht2016")
public class Attention implements java.io.Serializable {

	// Fields

	private Integer attendUid;
	private Users users;
	private Action action;
	private String content;
	private Timestamp enrollDate;

	// Constructors

	/** default constructor */
	public Attention() {
	}

	/** full constructor */
	public Attention(Users users, Action action, String content,
			Timestamp enrollDate) {
		this.users = users;
		this.action = action;
		this.content = content;
		this.enrollDate = enrollDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "attend_uid", unique = true, nullable = false)
	public Integer getAttendUid() {
		return this.attendUid;
	}

	public void setAttendUid(Integer attendUid) {
		this.attendUid = attendUid;
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
	@JoinColumn(name = "action_id")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Column(name = "content", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "enroll_date", length = 0)
	public Timestamp getEnrollDate() {
		return this.enrollDate;
	}

	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}

}