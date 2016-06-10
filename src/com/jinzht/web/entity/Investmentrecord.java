package com.jinzht.web.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Investmentrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "investmentrecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project","users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Investmentrecord implements java.io.Serializable {

	// Fields

	private Integer investId;
	private Users users;
	private Project project;
	private Integer statusId;
	private Float investAmount;
	private Date investDate;
	private String investCode;

	// Constructors

	/** default constructor */
	public Investmentrecord() {
	}

	/** minimal constructor */
	public Investmentrecord(Project project, Timestamp investDate) {
		this.project = project;
		this.investDate = investDate;
	}

	/** full constructor */
	public Investmentrecord(Users users, Project project, Integer statusId,
			 Float investAmount, Timestamp investDate) {
		this.users = users;
		this.project = project;
		this.statusId = statusId;
		this.investAmount = investAmount;
		this.investDate = investDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "invest_id", unique = true, nullable = false)
	public Integer getInvestId() {
		return this.investId;
	}

	public void setInvestId(Integer investId) {
		this.investId = investId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@OneToOne(fetch = FetchType.EAGER)
//	@PrimaryKeyJoinColumn
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "status_id")
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "invest_amount", precision = 12, scale = 0)
	public Float getInvestAmount() {
		return this.investAmount;
	}

	public void setInvestAmount(Float investAmount) {
		this.investAmount = investAmount;
	}

	@Column(name = "invest_date", nullable = false, length = 0)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getInvestDate() {
		return this.investDate;
	}

	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}
	@Column(name = "invest_code")
	public String getInvestCode() {
		return investCode;
	}

	public void setInvestCode(String investCode) {
		this.investCode = investCode;
	}

}