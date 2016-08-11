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

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "jinzht2016")
public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private Province province;
	private String name;
	private Boolean isInvlid;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(Province province, String name, Boolean isInvlid) {
		this.province = province;
		this.name = name;
		this.isInvlid = isInvlid;
	}

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
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