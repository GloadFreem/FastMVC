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
 * Inviterecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inviterecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users","systemcode","inviteDate"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Inviterecord implements java.io.Serializable {

	// Fields

	private Integer inviteId;
	private Users users;
	private Systemcode systemcode;
	private Date inviteDate;

	// Constructors

	/** default constructor */
	public Inviterecord() {
	}

	/** full constructor */
	public Inviterecord(Users users, Systemcode systemcode, Date inviteDate) {
		this.users = users;
		this.systemcode = systemcode;
		this.inviteDate = inviteDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "invite_id", unique = true, nullable = false)
	public Integer getInviteId() {
		return this.inviteId;
	}

	public void setInviteId(Integer inviteId) {
		this.inviteId = inviteId;
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
	@JoinColumn(name = "code_id")
	public Systemcode getSystemcode() {
		return this.systemcode;
	}

	public void setSystemcode(Systemcode systemcode) {
		this.systemcode = systemcode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "invite_date", length = 0)
	public Date getInviteDate() {
		return this.inviteDate;
	}

	public void setInviteDate(Date inviteDate) {
		this.inviteDate = inviteDate;
	}

}