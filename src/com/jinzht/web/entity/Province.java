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
 * Province entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "province", catalog = "jinzht2016")
public class Province implements java.io.Serializable {

	// Fields

	private Integer provinceId;
	private String name;
	private Boolean isInvlid;
	private Set<City> cities = new HashSet<City>(0);

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** full constructor */
	public Province(String name, Boolean isInvlid, Set<City> cities) {
		this.name = name;
		this.isInvlid = isInvlid;
		this.cities = cities;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "province_id", unique = true, nullable = false)
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "province")
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}