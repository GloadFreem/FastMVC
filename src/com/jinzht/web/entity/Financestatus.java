package com.jinzht.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Financestatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "financestatus", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"projects","statusId","isvalid"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Financestatus implements java.io.Serializable {
	// Fields
	private Integer statusId;
	private String name;
	private Short isvalid;
	private Set<Project> projects = new HashSet<Project>(0);

	// Constructors

	/** default constructor */
	public Financestatus() {
	}

	/** full constructor */
	public Financestatus(String name, Short isvalid, Set<Project> projects) {
		this.name = name;
		this.isvalid = isvalid;
		this.projects = projects;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isvalid")
	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "financestatus")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}