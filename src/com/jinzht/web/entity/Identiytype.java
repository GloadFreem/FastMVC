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

/**
 * Identiytype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "identiytype", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"authentics"})
public class Identiytype implements java.io.Serializable {

	// Fields

	private Short identiyTypeId;
	private String name;
	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public Identiytype() {
	}

	/** full constructor */
	public Identiytype(String name, Set<Authentic> authentics) {
		this.name = name;
		this.authentics = authentics;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "identiy_type_id", unique = true, nullable = false)
	public Short getIdentiyTypeId() {
		return this.identiyTypeId;
	}

	public void setIdentiyTypeId(Short identiyTypeId) {
		this.identiyTypeId = identiyTypeId;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "identiytype")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

}