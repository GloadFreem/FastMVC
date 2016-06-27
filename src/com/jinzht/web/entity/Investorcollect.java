package com.jinzht.web.entity;
// default package


import java.sql.Timestamp;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Investorcollect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "investorcollect", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"usersByUserId"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Investorcollect implements java.io.Serializable {

	// Fields

	private Integer collectId;
	private Users usersByUserId;
	private Users usersByUserCollectedId;
	private Date collectedDate;

	// Constructors

	/** default constructor */
	public Investorcollect() {
	}

	/** full constructor */
	public Investorcollect(Users usersByUserId, Users usersByUserCollectedId,
			Date collectedDate) {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_collected_id")
	public Users getUsersByUserCollectedId() {
		return this.usersByUserCollectedId;
	}

	public void setUsersByUserCollectedId(Users usersByUserCollectedId) {
		this.usersByUserCollectedId = usersByUserCollectedId;
	}

	@Column(name = "collected_date", length = 19)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")

	public Date getCollectedDate() {
		return this.collectedDate;
	}

	public void setCollectedDate(Date collectedDate) {
		this.collectedDate = collectedDate;
	}

}