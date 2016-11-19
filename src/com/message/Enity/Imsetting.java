package com.message.Enity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Imsetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "imsetting", catalog = "jinzht2016")
public class Imsetting implements java.io.Serializable {

	// Fields

	private Integer idimsetting;
	private String accessTokean;
	private Integer expires;
	private Date recordDate;

	// Constructors

	/** default constructor */
	public Imsetting() {
	}

	/** full constructor */
	public Imsetting(String accessTokean, Integer expires, Date recordDate) {
		this.accessTokean = accessTokean;
		this.expires = expires;
		this.recordDate = recordDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idimsetting", unique = true, nullable = false)
	public Integer getIdimsetting() {
		return this.idimsetting;
	}

	public void setIdimsetting(Integer idimsetting) {
		this.idimsetting = idimsetting;
	}

	@Column(name = "access_tokean", length = 500)
	public String getAccessTokean() {
		return this.accessTokean;
	}

	public void setAccessTokean(String accessTokean) {
		this.accessTokean = accessTokean;
	}

	@Column(name = "expires")
	public Integer getExpires() {
		return this.expires;
	}

	public void setExpires(Integer expires) {
		this.expires = expires;
	}

	@Column(name = "record_date", length = 19)
	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

}