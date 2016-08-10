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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "jinzht2016")
public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private String name;
	private Boolean isInvlid;
//	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City( String name, Boolean isInvlid) {
		this.name = name;
		this.isInvlid = isInvlid;
	}
//	public City(Province province, String name, Boolean isInvlid,
//			Set<Authentic> authentics) {
//		this.province = province;
//		this.name = name;
//		this.isInvlid = isInvlid;
//		this.authentics = authentics;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "city_id", unique = true, nullable = false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isInvlid")
	public Boolean getIsInvlid() {
		return this.isInvlid;
	}

	public void setIsInvlid(Boolean isInvlid) {
		this.isInvlid = isInvlid;
	}

}