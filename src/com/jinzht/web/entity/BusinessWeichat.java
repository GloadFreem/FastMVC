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
 * BusinessWeichat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_weichat", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"businessSchools"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BusinessWeichat implements java.io.Serializable {

	// Fields

	private Integer wid;
	private String name;
	private String telephone;
	private String wcode;
	private Set<BusinessSchool> businessSchools = new HashSet<BusinessSchool>(0);

	// Constructors

	/** default constructor */
	public BusinessWeichat() {
	}

	/** full constructor */
	public BusinessWeichat(String wcode, Set<BusinessSchool> businessSchools) {
		this.wcode = wcode;
		this.businessSchools = businessSchools;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wid", unique = true, nullable = false)
	public Integer getWid() {
		return this.wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	@Column(name = "wcode", length = 45)
	public String getWcode() {
		return this.wcode;
	}

	public void setWcode(String wcode) {
		this.wcode = wcode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessWeichat")
	public Set<BusinessSchool> getBusinessSchools() {
		return this.businessSchools;
	}

	public void setBusinessSchools(Set<BusinessSchool> businessSchools) {
		this.businessSchools = businessSchools;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="telephone",length=11)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}