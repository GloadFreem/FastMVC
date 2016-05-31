package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customservice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sustomservice", catalog = "jinzht2016")
public class Customservice implements java.io.Serializable {

	// Fields

	private Integer customServiceId;
	private String name;
	private String tel;

	// Constructors

	/** default constructor */
	public Customservice() {
	}

	/** full constructor */
	public Customservice(String name) {
		this.name = name;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "custom_service_id", unique = true, nullable = false)
	public Integer getCustomServiceId() {
		return this.customServiceId;
	}

	public void setCustomServiceId(Integer customServiceId) {
		this.customServiceId = customServiceId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}