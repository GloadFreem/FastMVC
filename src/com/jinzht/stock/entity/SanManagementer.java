package com.jinzht.stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SanManagementer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "san_managementer", catalog = "jinzht2016")
@JsonIgnoreProperties({"sanCompany"})
public class SanManagementer implements java.io.Serializable {

	// Fields

	private Integer id;
	private SanCompany sanCompany;
	private String name;
	private String position;

	// Constructors

	/** default constructor */
	public SanManagementer() {
	}

	/** full constructor */
	public SanManagementer(SanCompany sanCompany, String name, String position) {
		this.sanCompany = sanCompany;
		this.name = name;
		this.position = position;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public SanCompany getSanCompany() {
		return this.sanCompany;
	}

	public void setSanCompany(SanCompany sanCompany) {
		this.sanCompany = sanCompany;
	}

	@Column(name = "name", length = 16777215)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "position", length = 16777215)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}