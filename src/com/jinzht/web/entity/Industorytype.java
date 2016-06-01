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
 * Industorytype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "industorytype", catalog = "jinzht2016")
public class Industorytype implements java.io.Serializable {

	// Fields

	private Integer industoryId;
	private String name;
//	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public Industorytype() {
	}

	/** full constructor */
	public Industorytype(String name) {
		this.name = name;
	}
//	public Industorytype(String name, Set<Authentic> authentics) {
//		this.name = name;
//		this.authentics = authentics;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "industory_id", unique = true, nullable = false)
	public Integer getIndustoryId() {
		return this.industoryId;
	}

	public void setIndustoryId(Integer industoryId) {
		this.industoryId = industoryId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "industorytype")
//	public Set<Authentic> getAuthentics() {
//		return this.authentics;
//	}
//
//	public void setAuthentics(Set<Authentic> authentics) {
//		this.authentics = authentics;
//	}

}