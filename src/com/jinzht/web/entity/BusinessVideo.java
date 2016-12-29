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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * BusinessVideo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_video", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"businessSchool"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BusinessVideo implements java.io.Serializable {

	// Fields

	private Integer vid;
	private BusinessSchool businessSchool;
	private String vdesc;
	private String vname;
	private Integer vposition;
	private String vimage;
	private Integer vtimelong;
	private String vurl;

	// Constructors

	/** default constructor */
	public BusinessVideo() {
	}

	/** full constructor */
	public BusinessVideo(BusinessSchool businessSchool, String vdesc,
			String vname, Integer vposition, String vimage, Integer vtimelong) {
		this.businessSchool = businessSchool;
		this.vdesc = vdesc;
		this.vname = vname;
		this.vposition = vposition;
		this.vimage = vimage;
		this.vtimelong = vtimelong;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vid", unique = true, nullable = false)
	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resourceid")
	public BusinessSchool getBusinessSchool() {
		return this.businessSchool;
	}

	public void setBusinessSchool(BusinessSchool businessSchool) {
		this.businessSchool = businessSchool;
	}

	@Column(name = "vdesc", length = 16777215)
	public String getVdesc() {
		return this.vdesc;
	}

	public void setVdesc(String vdesc) {
		this.vdesc = vdesc;
	}

	@Column(name = "vname")
	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	@Column(name = "vposition")
	public Integer getVposition() {
		return this.vposition;
	}

	public void setVposition(Integer vposition) {
		this.vposition = vposition;
	}

	@Column(name = "vimage")
	public String getVimage() {
		return this.vimage;
	}

	public void setVimage(String vimage) {
		this.vimage = vimage;
	}

	@Column(name = "vtimelong")
	public Integer getVtimelong() {
		return this.vtimelong;
	}

	public void setVtimelong(Integer vtimelong) {
		this.vtimelong = vtimelong;
	}

	@Column(name = "vurl")
	public String getVurl() {
		return vurl;
	}

	public void setVurl(String vurl) {
		this.vurl = vurl;
	}

}