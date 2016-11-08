package com.message.Enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MsgImages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_images", catalog = "jinzht2016")
@JsonIgnoreProperties({"msg"})
public class MsgImages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Msg msg;
	private String url;

	// Constructors

	/** default constructor */
	public MsgImages() {
	}

	/** full constructor */
	public MsgImages(Msg msg, String url) {
		this.msg = msg;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "image_id", unique = true, nullable = false)
	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info_id")
	public Msg getMsg() {
		return this.msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}