package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProjectAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project_address", catalog = "jinzht2016")
public class ProjectAddress implements java.io.Serializable {

	// Fields

	private Integer idAd;
	private String value;
	private Integer key;

	// Constructors

	/** default constructor */
	public ProjectAddress() {
	}

	/** full constructor */
	public ProjectAddress(String value, Integer key) {
		this.value = value;
		this.key = key;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_ad", unique = true, nullable = false)
	public Integer getIdAd() {
		return this.idAd;
	}

	public void setIdAd(Integer idAd) {
		this.idAd = idAd;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "key")
	public Integer getKey() {
		return this.key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

}