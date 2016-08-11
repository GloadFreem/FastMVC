package com.jinzht.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jinzht.tools.Config;

/**
 * Userstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userstatus", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"userses"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Userstatus implements java.io.Serializable {

	// Fields

	private Integer userStatusId;
	private String name;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Userstatus() {
	}

	/** minimal constructor */
	public Userstatus(String name) {
		this.name = name;
	}

	/** full constructor */
	public Userstatus(String name, Set<Users> userses) {
		this.name = name;
		this.userses = userses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_status_id", unique = true, nullable = false)
	public Integer getUserStatusId() {
		return this.userStatusId;
	}

	public void setUserStatusId(Integer userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Column(name = "name", nullable = false, length = 11)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userstatus")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}