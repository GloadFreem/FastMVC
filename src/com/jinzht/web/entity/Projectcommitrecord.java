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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Projectcommitrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "projectcommitrecord", catalog = "jinzht2016")
public class Projectcommitrecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private Users users;
	private Project project;
	private Status status;
	private Date recordDate;

	// Constructors

	/** default constructor */
	public Projectcommitrecord() {
	}

	/** minimal constructor */
	public Projectcommitrecord(Users users) {
		this.users = users;
	}

	/** full constructor */
	public Projectcommitrecord(Users users, Project project, Status status,
			Date recordDate) {
		this.users = users;
		this.project = project;
		this.status = status;
		this.recordDate = recordDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "record_id", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "record_date", length = 0)
	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

}