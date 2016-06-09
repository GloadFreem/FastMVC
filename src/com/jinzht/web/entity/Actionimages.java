package com.jinzht.web.entity;
// default package

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
 * Actionimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "actionimages", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"action"})
public class Actionimages implements java.io.Serializable {

	// Fields

	private Integer imgId;
	private Action action;
	private String url;

	// Constructors

	/** default constructor */
	public Actionimages() {
	}

	/** full constructor */
	public Actionimages(Action action, String url) {
		this.action = action;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "img_id", unique = true, nullable = false)
	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_id")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Column(name = "url", length = 45)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}