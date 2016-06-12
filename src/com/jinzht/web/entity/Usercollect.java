package com.jinzht.web.entity;
// default package

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
 * Usercollect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usercollect", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"usersByUserCollectedId"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Usercollect implements java.io.Serializable {

	// Fields

	private Integer collecteId;
	private Users usersByUserId;
	private Users usersByUserCollectedId;

	// Constructors

	/** default constructor */
	public Usercollect() {
	}

	/** full constructor */
	public Usercollect(Users usersByUserId, Users usersByUserCollectedId) {
		this.usersByUserId = usersByUserId;
		this.usersByUserCollectedId = usersByUserCollectedId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "collecte_id", unique = true, nullable = false)
	public Integer getCollecteId() {
		return this.collecteId;
	}

	public void setCollecteId(Integer collecteId) {
		this.collecteId = collecteId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsersByUserId() {
		return this.usersByUserId;
	}

	public void setUsersByUserId(Users usersByUserId) {
		this.usersByUserId = usersByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_collected_id")
	public Users getUsersByUserCollectedId() {
		return this.usersByUserCollectedId;
	}

	public void setUsersByUserCollectedId(Users usersByUserCollectedId) {
		this.usersByUserCollectedId = usersByUserCollectedId;
	}

}