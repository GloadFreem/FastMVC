package com.jinzht.web.entity;
// default package


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
 * Contentshare entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contentshare", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"publiccontents"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Contentshare implements java.io.Serializable {

	// Fields

	private Integer shareId;
	private Contenttype contenttype;
	private String image;
	private String desc;
	private String tag;
	private String content;
	private Set<Publiccontent> publiccontents = new HashSet<Publiccontent>(0);

	// Constructors

	/** default constructor */
	public Contentshare() {
	}

	/** full constructor */
	public Contentshare(Contenttype contenttype, String image, String desc,
			String content, Set<Publiccontent> publiccontents) {
		this.contenttype = contenttype;
		this.image = image;
		this.desc = desc;
		this.content = content;
		this.publiccontents = publiccontents;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "share_id", unique = true, nullable = false)
	public Integer getShareId() {
		return this.shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "content_type_id")
	public Contenttype getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(Contenttype contenttype) {
		this.contenttype = contenttype;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "description")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contentshare")
	public Set<Publiccontent> getPubliccontents() {
		return this.publiccontents;
	}

	public void setPubliccontents(Set<Publiccontent> publiccontents) {
		this.publiccontents = publiccontents;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}