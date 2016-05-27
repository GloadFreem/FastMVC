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

/**
 * Systemuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "systemuser", catalog = "jinzht2016")
public class Systemuser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Roletype roletype;
	private Boolean password;

	// Constructors

	/** default constructor */
	public Systemuser() {
	}

	/** full constructor */
	public Systemuser(Roletype roletype, Boolean password) {
		this.roletype = roletype;
		this.password = password;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_type_id")
	public Roletype getRoletype() {
		return this.roletype;
	}

	public void setRoletype(Roletype roletype) {
		this.roletype = roletype;
	}

	@Column(name = "password")
	public Boolean getPassword() {
		return this.password;
	}

	public void setPassword(Boolean password) {
		this.password = password;
	}

}