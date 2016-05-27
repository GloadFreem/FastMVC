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

/**
 * Status entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "status", catalog = "jinzht2016")
public class Status implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private String name;
	private Set<Projectcommitrecord> projectcommitrecords = new HashSet<Projectcommitrecord>(
			0);

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** full constructor */
	public Status(String name, Set<Projectcommitrecord> projectcommitrecords) {
		this.name = name;
		this.projectcommitrecords = projectcommitrecords;
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

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "status")
	public Set<Projectcommitrecord> getProjectcommitrecords() {
		return this.projectcommitrecords;
	}

	public void setProjectcommitrecords(
			Set<Projectcommitrecord> projectcommitrecords) {
		this.projectcommitrecords = projectcommitrecords;
	}

}