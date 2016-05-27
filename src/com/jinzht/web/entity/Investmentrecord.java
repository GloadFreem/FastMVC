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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Investmentrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "investmentrecord", catalog = "jinzht2016")
public class Investmentrecord implements java.io.Serializable {

	// Fields

	private Integer investId;
	private Users users;
	private Project project;
	private Integer statusId;
	private Integer projectId;
	private Float investAmount;
	private Timestamp investDate;

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
			Integer projectId, Float investAmount, Timestamp investDate) {
		this.users = users;
		this.project = project;
		this.statusId = statusId;
		this.projectId = projectId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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

	@Column(name = "project_id")
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "invest_amount", precision = 12, scale = 0)
	public Float getInvestAmount() {
		return this.investAmount;
	}

	public void setInvestAmount(Float investAmount) {
		this.investAmount = investAmount;
	}

	@Column(name = "invest_date", nullable = false, length = 0)
	public Timestamp getInvestDate() {
		return this.investDate;
	}

	public void setInvestDate(Timestamp investDate) {
		this.investDate = investDate;
	}

}