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
 * Loginfailrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loginfailrecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Loginfailrecord implements java.io.Serializable {

	// Fields

	private Integer failId;
	private Users users;
	private Short count;
	private Short platform;
	private Date loginFailDate;

	// Constructors

	/** default constructor */
	public Loginfailrecord() {
	}

	/** full constructor */
	public Loginfailrecord(Users users, Short count, Short platform,
			Date loginFailDate) {
		this.users = users;
		this.count = count;
		this.platform = platform;
		this.loginFailDate = loginFailDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "fail_id", unique = true, nullable = false)
	public Integer getFailId() {
		return this.failId;
	}

	public void setFailId(Integer failId) {
		this.failId = failId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "count")
	public Short getCount() {
		return this.count;
	}

	public void setCount(Short count) {
		this.count = count;
	}

	@Column(name = "platform")
	public Short getPlatform() {
		return this.platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "login_fail_date", length = 0)
	public Date getLoginFailDate() {
		return this.loginFailDate;
	}

	public void setLoginFailDate(Date loginFailDate) {
		this.loginFailDate = loginFailDate;
	}

}