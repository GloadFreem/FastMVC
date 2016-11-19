package com.message.Enity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Newsbanner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "newsbanner", catalog = "jinzht2016")
public class Newsbanner implements java.io.Serializable {

	// Fields

	private Integer bannerId;
	private String image;
	private String url;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String desc;

	// Constructors

	/** default constructor */
	public Newsbanner() {
	}

	/** full constructor */
	public Newsbanner(String image, String url, Timestamp beginTime,
			Timestamp endTime, String desc) {
		this.image = image;
		this.url = url;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.desc = desc;
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

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "beginTime", length = 19)
	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "endTime", length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "desc")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}