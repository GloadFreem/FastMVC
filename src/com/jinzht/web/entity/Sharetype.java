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
 * Sharetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sharetype", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"shares"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Sharetype implements java.io.Serializable {

	// Fields

	private Integer shareTypeId;
	private String name;
	private Set<Share> shares = new HashSet<Share>(0);

	// Constructors

	/** default constructor */
	public Sharetype() {
	}

	/** full constructor */
	public Sharetype(String name, Set<Share> shares) {
		this.name = name;
		this.shares = shares;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "share_type_id", unique = true, nullable = false)
	public Integer getShareTypeId() {
		return this.shareTypeId;
	}

	public void setShareTypeId(Integer shareTypeId) {
		this.shareTypeId = shareTypeId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sharetype")
	public Set<Share> getShares() {
		return this.shares;
	}

	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}

}