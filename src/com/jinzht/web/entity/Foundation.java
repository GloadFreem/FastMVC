package com.jinzht.web.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Foundation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "foundation", catalog = "jinzht2016")
public class Foundation implements java.io.Serializable {

	// Fields

	private Integer foundationId;
	private String name;
	private String image;
	private String content;
	private String url;

	// Constructors

	/** default constructor */
	public Foundation() {
	}

	/** full constructor */
	public Foundation(String name, String image, String content, String url) {
		this.name = name;
		this.image = image;
		this.content = content;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "foundation_id", unique = true, nullable = false)
	public Integer getFoundationId() {
		return this.foundationId;
	}

	public void setFoundationId(Integer foundationId) {
		this.foundationId = foundationId;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "image", length = 200)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}