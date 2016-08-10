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

/**
 * Contentimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contentimages", catalog = "jinzht2016")
public class Contentimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Publiccontent publiccontent;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public Contentimages() {
	}

	/** full constructor */
	public Contentimages(Publiccontent publiccontent, Boolean isvalid) {
		this.publiccontent = publiccontent;
		this.isvalid = isvalid;
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
	@JoinColumn(name = "public_content_id")
	public Publiccontent getPubliccontent() {
		return this.publiccontent;
	}

	public void setPubliccontent(Publiccontent publiccontent) {
		this.publiccontent = publiccontent;
	}

	@Column(name = "isvalid")
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}