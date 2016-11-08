package com.jinzht.web.entity;

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
 * Webcontentimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "webcontentimages", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"weburlrecord"})
public class Webcontentimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Weburlrecord weburlrecord;
	private String url;

	// Constructors

	/** default constructor */
	public Webcontentimages() {
	}

	/** full constructor */
	public Webcontentimages(Weburlrecord weburlrecord, String url) {
		this.weburlrecord = weburlrecord;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "image_id", unique = true, nullable = false)
	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	public Weburlrecord getWeburlrecord() {
		return this.weburlrecord;
	}

	public void setWeburlrecord(Weburlrecord weburlrecord) {
		this.weburlrecord = weburlrecord;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}