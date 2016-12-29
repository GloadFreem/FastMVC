package com.jinzht.web.entity;
// default package

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
 * Speechmarker entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "speechmarker", catalog = "jinzht2016")
@JsonIgnoreProperties(value = { "businessSchools" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Speechmarker implements java.io.Serializable {

	// Fields

	private Integer markerId;
	private String name;
	private String image;
	private String desc;
	private Set<BusinessSchool> businessSchools = new HashSet<BusinessSchool>(0);

	// Constructors

	/** default constructor */
	public Speechmarker() {
	}

	/** full constructor */
	public Speechmarker(String name, String image, String desc,
			Set<BusinessSchool> businessSchools) {
		this.name = name;
		this.image = image;
		this.desc = desc;
		this.businessSchools = businessSchools;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "marker_id", unique = true, nullable = false)
	public Integer getMarkerId() {
		return this.markerId;
	}

	public void setMarkerId(Integer markerId) {
		this.markerId = markerId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "desciption")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "speechmarker")
	public Set<BusinessSchool> getBusinessSchools() {
		return this.businessSchools;
	}

	public void setBusinessSchools(Set<BusinessSchool> businessSchools) {
		this.businessSchools = businessSchools;
	}

}