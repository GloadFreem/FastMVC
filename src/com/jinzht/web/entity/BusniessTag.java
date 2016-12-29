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
 * BusniessTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "busniess_tag", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"businessSchools"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BusniessTag implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String tname;
	private Set<BusinessSchool> businessSchools = new HashSet<BusinessSchool>(0);

	// Constructors

	/** default constructor */
	public BusniessTag() {
	}

	/** full constructor */
	public BusniessTag(String tname, Set<BusinessSchool> businessSchools) {
		this.tname = tname;
		this.businessSchools = businessSchools;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tid", unique = true, nullable = false)
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@Column(name = "tname")
	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "busniessTag")
	public Set<BusinessSchool> getBusinessSchools() {
		return this.businessSchools;
	}

	public void setBusinessSchools(Set<BusinessSchool> businessSchools) {
		this.businessSchools = businessSchools;
	}

}