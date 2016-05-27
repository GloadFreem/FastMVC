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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Messagetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "messagetype", catalog = "jinzht2016")
public class Messagetype implements java.io.Serializable {

	// Fields

	private Integer messageTypeId;
	private String name;
	private Set<Systemmessage> systemmessages = new HashSet<Systemmessage>(0);

	// Constructors

	/** default constructor */
	public Messagetype() {
	}

	/** full constructor */
	public Messagetype(String name, Set<Systemmessage> systemmessages) {
		this.name = name;
		this.systemmessages = systemmessages;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "message_type_id", unique = true, nullable = false)
	public Integer getMessageTypeId() {
		return this.messageTypeId;
	}

	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "messagetype")
	public Set<Systemmessage> getSystemmessages() {
		return this.systemmessages;
	}

	public void setSystemmessages(Set<Systemmessage> systemmessages) {
		this.systemmessages = systemmessages;
	}

}