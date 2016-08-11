package com.jinzht.web.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Systemcode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "systemcode", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"inviterecords","users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Systemcode implements java.io.Serializable {

	// Fields

	private Integer codeId;
	private Users users;
	private String code;
	private Set<Inviterecord> inviterecords = new HashSet<Inviterecord>(0);

	// Constructors

	/** default constructor */
	public Systemcode() {
	}

	/** full constructor */
	public Systemcode(Users users, String code, Set<Inviterecord> inviterecords) {
		this.users = users;
		this.code = code;
		this.inviterecords = inviterecords;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "code_id", unique = true, nullable = false)
	public Integer getCodeId() {
		return this.codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "systemcode")
	public Set<Inviterecord> getInviterecords() {
		return this.inviterecords;
	}

	public void setInviterecords(Set<Inviterecord> inviterecords) {
		this.inviterecords = inviterecords;
	}

}