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
 * Feeingtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feelingtype", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"publiccontents"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Feelingtype implements java.io.Serializable {

	// Fields

	private Integer feelingTypeId;
	private String name;
	private Set<Publiccontent> publiccontents = new HashSet<Publiccontent>(0);

	// Constructors

	/** default constructor */
	public Feelingtype() {
	}

	/** full constructor */
	public Feelingtype(String name, Set<Publiccontent> publiccontents) {
		this.name = name;
		this.publiccontents = publiccontents;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feeling_type_id", unique = true, nullable = false)
	public Integer getFeelingTypeId() {
		return this.feelingTypeId;
	}

	public void setFeelingTypeId(Integer feelingTypeId) {
		this.feelingTypeId = feelingTypeId;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "feeingtype")
	public Set<Publiccontent> getPubliccontents() {
		return this.publiccontents;
	}

	public void setPubliccontents(Set<Publiccontent> publiccontents) {
		this.publiccontents = publiccontents;
	}

}