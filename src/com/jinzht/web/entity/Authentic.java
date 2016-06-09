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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Authentic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authentic", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Authentic implements java.io.Serializable {

	// Fields

	private Integer authId;
	private Industoryarea industoryarea;
	private Users users;
	private Identiytype identiytype;
	private Industorytype industorytype;
	private Authenticstatus authenticstatus;
	private City city;
	private String name;
	private String identiyCarA;
	private String identiyCarB;
	private String identiyCarNo;
	private String companyName;
	private String companyAddress;
	private String position;
	private String buinessLicence;
	private String buinessLicenceNo;
	private String introduce;
	private String companyIntroduce;
	private Short optional;
	private Set<Autrhrecord> autrhrecords = new HashSet<Autrhrecord>(0);

	// Constructors

	/** default constructor */
	public Authentic() {
	}

	/** full constructor */
	public Authentic(Industoryarea industoryarea, Users users,
			Identiytype identiytype, Industorytype industorytype,
			Authenticstatus authenticstatus, City city, String name,
			String identiyCarA, String identiyCarB, String identiyCarNo,
			String companyName, String companyAddress, String position,
			String buinessLicence, String buinessLicenceNo, String introduce,
			String companyIntroduce, Short optional,
			Set<Autrhrecord> autrhrecords) {
		this.industoryarea = industoryarea;
		this.users = users;
		this.identiytype = identiytype;
		this.industorytype = industorytype;
		this.authenticstatus = authenticstatus;
		this.city = city;
		this.name = name;
		this.identiyCarA = identiyCarA;
		this.identiyCarB = identiyCarB;
		this.identiyCarNo = identiyCarNo;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.position = position;
		this.buinessLicence = buinessLicence;
		this.buinessLicenceNo = buinessLicenceNo;
		this.introduce = introduce;
		this.companyIntroduce = companyIntroduce;
		this.optional = optional;
		this.autrhrecords = autrhrecords;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "auth_id", unique = true, nullable = false)
	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id")
	public Industoryarea getIndustoryarea() {
		return this.industoryarea;
	}

	public void setIndustoryarea(Industoryarea industoryarea) {
		this.industoryarea = industoryarea;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "identiy_type_id")
	public Identiytype getIdentiytype() {
		return this.identiytype;
	}

	public void setIdentiytype(Identiytype identiytype) {
		this.identiytype = identiytype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "industory_id")
	public Industorytype getIndustorytype() {
		return this.industorytype;
	}

	public void setIndustorytype(Industorytype industorytype) {
		this.industorytype = industorytype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	public Authenticstatus getAuthenticstatus() {
		return this.authenticstatus;
	}

	public void setAuthenticstatus(Authenticstatus authenticstatus) {
		this.authenticstatus = authenticstatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Identiy_car_a")
	public String getIdentiyCarA() {
		return this.identiyCarA;
	}

	public void setIdentiyCarA(String identiyCarA) {
		this.identiyCarA = identiyCarA;
	}

	@Column(name = "Identiy_car_b")
	public String getIdentiyCarB() {
		return this.identiyCarB;
	}

	public void setIdentiyCarB(String identiyCarB) {
		this.identiyCarB = identiyCarB;
	}

	@Column(name = "Identiy_car_no", length = 20)
	public String getIdentiyCarNo() {
		return this.identiyCarNo;
	}

	public void setIdentiyCarNo(String identiyCarNo) {
		this.identiyCarNo = identiyCarNo;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "company_address", length = 10)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "position", length = 20)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "Buiness_licence", length = 200)
	public String getBuinessLicence() {
		return this.buinessLicence;
	}

	public void setBuinessLicence(String buinessLicence) {
		this.buinessLicence = buinessLicence;
	}

	@Column(name = "Buiness_licence_no", length = 50)
	public String getBuinessLicenceNo() {
		return this.buinessLicenceNo;
	}

	public void setBuinessLicenceNo(String buinessLicenceNo) {
		this.buinessLicenceNo = buinessLicenceNo;
	}

	@Column(name = "introduce")
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(name = "company_introduce")
	public String getCompanyIntroduce() {
		return this.companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	@Column(name = "optional")
	public Short getOptional() {
		return this.optional;
	}

	public void setOptional(Short optional) {
		this.optional = optional;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "authentic")
	public Set<Autrhrecord> getAutrhrecords() {
		return this.autrhrecords;
	}

	public void setAutrhrecords(Set<Autrhrecord> autrhrecords) {
		this.autrhrecords = autrhrecords;
	}

}