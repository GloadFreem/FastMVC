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
 * Roletype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roletype", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"systemusers"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Roletype implements java.io.Serializable {

	// Fields

	private Integer roleTypeId;
	private String name;
	private Set<Systemuser> systemusers = new HashSet<Systemuser>(0);

	// Constructors

	/** default constructor */
	public Roletype() {
	}

	/** full constructor */
	public Roletype(String name, Set<Systemuser> systemusers) {
		this.name = name;
		this.systemusers = systemusers;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_type_id", unique = true, nullable = false)
	public Integer getRoleTypeId() {
		return this.roleTypeId;
	}

	public void setRoleTypeId(Integer roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roletype")
	public Set<Systemuser> getSystemusers() {
		return this.systemusers;
	}

	public void setSystemusers(Set<Systemuser> systemusers) {
		this.systemusers = systemusers;
	}

}