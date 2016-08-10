package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Banner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "banner", catalog = "jinzht2016")
public class Banner implements java.io.Serializable {

	// Fields

	private Integer bannerId;
	private String name;
	private String description;
	private String image;
	private String bannerType;
	private String url;

	// Constructors

	/** default constructor */
	public Banner() {
	}

	/** full constructor */
	public Banner(String name, String description, String image,
			String bannerType, String url) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.bannerType = bannerType;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "banner_id", unique = true, nullable = false)
	public Integer getBannerId() {
		return this.bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image", length = 100)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "banner_type", length = 100)
	public String getBannerType() {
		return this.bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}