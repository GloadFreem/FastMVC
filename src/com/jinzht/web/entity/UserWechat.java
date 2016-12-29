package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * UserWechat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_wechat", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserWechat implements java.io.Serializable {

	// Fields

	private Integer wid;
	private String wcode;
	private String wtel;

	// Constructors

	/** default constructor */
	public UserWechat() {
	}

	/** full constructor */
	public UserWechat(String wcode, String wtel) {
		this.wcode = wcode;
		this.wtel = wtel;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wid", unique = true, nullable = false)
	public Integer getWid() {
		return this.wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	@Column(name = "wcode")
	public String getWcode() {
		return this.wcode;
	}

	public void setWcode(String wcode) {
		this.wcode = wcode;
	}

	@Column(name = "wtel")
	public String getWtel() {
		return this.wtel;
	}

	public void setWtel(String wtel) {
		this.wtel = wtel;
	}

}