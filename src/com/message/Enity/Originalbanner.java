package com.message.Enity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Originalbanner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "originalbanner", catalog = "jinzht2016")
public class Originalbanner implements java.io.Serializable {

	// Fields

	private Integer bannerId;
	private String image;
	private String url;
	private Short type;
	private Timestamp startTime;
	private Timestamp endTime;
	private String desc;

	// Constructors

	/** default constructor */
	public Originalbanner() {
	}

	/** full constructor */
	public Originalbanner(String image, String url, Short type,
			Timestamp startTime, Timestamp endTime, String desc) {
		this.image = image;
		this.url = url;
		this.type = type;
		this.startTime = startTime;
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

	@Column(name = "type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "start_time", length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 19)
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