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
 * Industoryarea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "industoryarea", catalog = "jinzht2016")
@JsonIgnoreProperties(value={""})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Industoryarea implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private String name;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public Industoryarea() {
	}

	/** full constructor */
	public Industoryarea(String name, Boolean isvalid) {
		this.name = name;
		this.isvalid = isvalid;
	}
//	public Industoryarea(String name, Boolean isvalid, Set<Authentic> authentics) {
//		this.name = name;
//		this.isvalid = isvalid;
//		this.authentics = authentics;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "area_id", unique = true, nullable = false)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isvalid")
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
}