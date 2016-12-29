package com.jinzht.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * BusniessJoin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "busniess_join", catalog = "jinzht2016")
//@JsonIgnoreProperties(value={"businessSchool"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BusniessJoin implements java.io.Serializable {

	// Fields

	private Integer jid;
	private BusinessSchool businessSchool;
	private BusinessInvitationCode businessInvitationCode;
	private Users users;
	private String jtel;
	private Date cattendtime;

	// Constructors

	/** default constructor */
	public BusniessJoin() {
	}

	/** full constructor */
	public BusniessJoin(BusinessSchool businessSchool,
			BusinessInvitationCode businessInvitationCode, Users users,
			String jtel, Date cattendtime) {
		this.businessSchool = businessSchool;
		this.businessInvitationCode = businessInvitationCode;
		this.users = users;
		this.jtel = jtel;
		this.cattendtime = cattendtime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "jid", unique = true, nullable = false)
	public Integer getJid() {
		return this.jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resourceid")
	public BusinessSchool getBusinessSchool() {
		return this.businessSchool;
	}

	public void setBusinessSchool(BusinessSchool businessSchool) {
		this.businessSchool = businessSchool;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codeid")
	public BusinessInvitationCode getBusinessInvitationCode() {
		return this.businessInvitationCode;
	}

	public void setBusinessInvitationCode(
			BusinessInvitationCode businessInvitationCode) {
		this.businessInvitationCode = businessInvitationCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "jtel")
	public String getJtel() {
		return this.jtel;
	}

	public void setJtel(String jtel) {
		this.jtel = jtel;
	}

	@Column(name = "cattendtime", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getCattendtime() {
		return this.cattendtime;
	}

	public void setCattendtime(Date cattendtime) {
		this.cattendtime = cattendtime;
	}

}