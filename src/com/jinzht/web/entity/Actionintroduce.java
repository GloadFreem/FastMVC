package com.jinzht.web.entity;
// default package


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Actionintroduce entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "actionintroduce", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"action"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Actionintroduce implements java.io.Serializable {

	// Fields

	private Integer introduceId;
	private Action action;
	private Short type;
	private String content;

	// Constructors

	/** default constructor */
	public Actionintroduce() {
	}

	/** minimal constructor */
	public Actionintroduce(Integer introduceId) {
		this.introduceId = introduceId;
	}

	/** full constructor */
	public Actionintroduce(Integer introduceId, Action action, Short type,
			String content) {
		this.introduceId = introduceId;
		this.action = action;
		this.type = type;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "introduce_id", unique = true, nullable = false)
	public Integer getIntroduceId() {
		return this.introduceId;
	}

	public void setIntroduceId(Integer introduceId) {
		this.introduceId = introduceId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "action_id")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Column(name = "type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}