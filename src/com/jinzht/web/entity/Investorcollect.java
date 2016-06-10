package com.jinzht.web.entity;
// default package


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
 * Investorcollect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "investorcollect", catalog = "jinzht2016")
public class Investorcollect implements java.io.Serializable {

	// Fields

	private Integer collectId;
	private Users usersByUserId;
	private Users usersByUserCollectedId;
	private Timestamp collectedDate;

	// Constructors

	/** default constructor */
	public Investorcollect() {
	}

	/** full constructor */
	public Investorcollect(Users usersByUserId, Users usersByUserCollectedId,
			Timestamp collectedDate) {
		this.usersByUserId = usersByUserId;
		this.usersByUserCollectedId = usersByUserCollectedId;
		this.collectedDate = collectedDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "collect_id", unique = true, nullable = false)
	public Integer getCollectId() {
		return this.collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@Column(name = "collected_date", length = 19)
	public Timestamp getCollectedDate() {
		return this.collectedDate;
	}

	public void setCollectedDate(Timestamp collectedDate) {
		this.collectedDate = collectedDate;
	}

}