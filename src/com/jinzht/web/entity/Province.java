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

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** full constructor */
	public Province(String name, Boolean isInvlid) {
		this.name = name;
		this.isInvlid = isInvlid;
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


}