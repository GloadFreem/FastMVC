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
 * SanAsset entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "san_asset", catalog = "jinzht2016")
@JsonIgnoreProperties({"sanCompany"})
public class SanAsset implements java.io.Serializable {

	// Fields

	private Integer id;
	private SanCompany sanCompany;
	private String year;
	private String sanAssetcol;
	private String allAsset;

	// Constructors

	/** default constructor */
	public SanAsset() {
	}

	/** full constructor */
	public SanAsset(SanCompany sanCompany, String year, String sanAssetcol,
			String allAsset) {
		this.sanCompany = sanCompany;
		this.year = year;
		this.sanAssetcol = sanAssetcol;
		this.allAsset = allAsset;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	public SanCompany getSanCompany() {
		return this.sanCompany;
	}

	public void setSanCompany(SanCompany sanCompany) {
		this.sanCompany = sanCompany;
	}

	@Column(name = "year")
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "san_assetcol")
	public String getSanAssetcol() {
		return this.sanAssetcol;
	}

	public void setSanAssetcol(String sanAssetcol) {
		this.sanAssetcol = sanAssetcol;
	}

	@Column(name = "all_asset")
	public String getAllAsset() {
		return this.allAsset;
	}

	public void setAllAsset(String allAsset) {
		this.allAsset = allAsset;
	}

}