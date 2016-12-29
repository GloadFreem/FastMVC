package com.jinzht.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * BusinessJionRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_jion_record", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BusinessJionRecord implements java.io.Serializable {

	// Fields

	private Integer rid;
	private Date rstarttime;
	private Date rendtime;
	private String risend;
	private String rtime;

	// Constructors

	/** default constructor */
	public BusinessJionRecord() {
	}

	/** full constructor */
	public BusinessJionRecord(Date rstarttime, Date rendtime,
			String risend, String rtime) {
		this.rstarttime = rstarttime;
		this.rendtime = rendtime;
		this.risend = risend;
		this.rtime = rtime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rid", unique = true, nullable = false)
	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(name = "rstarttime", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getRstarttime() {
		return this.rstarttime;
	}

	public void setRstarttime(Date rstarttime) {
		this.rstarttime = rstarttime;
	}

	@Column(name = "rendtime", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getRendtime() {
		return this.rendtime;
	}

	public void setRendtime(Date rendtime) {
		this.rendtime = rendtime;
	}

	@Column(name = "risend")
	public String getRisend() {
		return this.risend;
	}

	public void setRisend(String risend) {
		this.risend = risend;
	}

	@Column(name = "rtime")
	public String getRtime() {
		return this.rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

}