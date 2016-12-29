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
 * BusinessContentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_content_type", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"businessSchools"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BusinessContentType implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private Set<BusinessSchool> businessSchools = new HashSet<BusinessSchool>(0);

	// Constructors

	/** default constructor */
	public BusinessContentType() {
	}

	/** full constructor */
	public BusinessContentType(String cname, Set<BusinessSchool> businessSchools) {
		this.cname = cname;
		this.businessSchools = businessSchools;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "cname")
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessContentType")
	public Set<BusinessSchool> getBusinessSchools() {
		return this.businessSchools;
	}

	public void setBusinessSchools(Set<BusinessSchool> businessSchools) {
		this.businessSchools = businessSchools;
	}

}