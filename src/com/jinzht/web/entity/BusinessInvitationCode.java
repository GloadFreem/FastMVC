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

/**
 * BusinessInvitationCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_invitation_code", catalog = "jinzht2016")
@JsonIgnoreProperties(value = { "busniessJoins" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BusinessInvitationCode implements java.io.Serializable {

	// Fields

	private Integer cid;
	private BusinessSchool businessSchool;
	private String ccode;
	private String cvalid;
	private Set<BusniessJoin> busniessJoins = new HashSet<BusniessJoin>(0);

	// Constructors

	/** default constructor */
	public BusinessInvitationCode() {
	}

	/** full constructor */
	public BusinessInvitationCode(BusinessSchool businessSchool,
			Integer resourceid, String ccode, String cvalid,
			Set<BusniessJoin> busniessJoins) {
		this.ccode = ccode;
		this.cvalid = cvalid;
		this.busniessJoins = busniessJoins;
		this.businessSchool = businessSchool;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resourceid")
	public BusinessSchool getBusinessSchool() {
		return this.businessSchool;
	}

	public void setBusinessSchool(BusinessSchool businessSchool) {
		this.businessSchool = businessSchool;
	}

	@Column(name = "ccode")
	public String getCcode() {
		return this.ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	@Column(name = "cvalid")
	public String getCvalid() {
		return this.cvalid;
	}

	public void setCvalid(String cvalid) {
		this.cvalid = cvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessInvitationCode")
	public Set<BusniessJoin> getBusniessJoins() {
		return this.busniessJoins;
	}

	public void setBusniessJoins(Set<BusniessJoin> busniessJoins) {
		this.busniessJoins = busniessJoins;
	}

}