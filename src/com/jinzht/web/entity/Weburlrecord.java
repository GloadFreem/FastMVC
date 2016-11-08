package com.jinzht.web.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Weburlrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "weburlrecord", catalog = "jinzht2016")
public class Weburlrecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private Contenttype contenttype;
	private Webcontenttype webcontenttype;
	private String title;
	private String tag;
	private String url;
	private String content;
	private Date createDate;
	private String image;
	private String orignal;
	private Boolean flag;
	private List images = new ArrayList();
	private Set<Webcontentimages> webcontentimageses = new HashSet<Webcontentimages>(
			0);

	// Constructors

	/** default constructor */
	public Weburlrecord() {
	}

	/** minimal constructor */
	public Weburlrecord(Contenttype contenttype, String title, String tag,
			String url, String content, Date createDate, Boolean flag) {
		this.contenttype = contenttype;
		this.title = title;
		this.tag = tag;
		this.url = url;
		this.content = content;
		this.createDate = createDate;
		this.flag = flag;
	}

	/** full constructor */
	public Weburlrecord(Contenttype contenttype, Webcontenttype webcontenttype,
			String title, String tag, String url, String content,
			Timestamp createDate, String image, String orignal, Boolean flag,
			Set<Webcontentimages> webcontentimageses) {
		this.contenttype = contenttype;
		this.webcontenttype = webcontenttype;
		this.title = title;
		this.tag = tag;
		this.url = url;
		this.content = content;
		this.createDate = createDate;
		this.image = image;
		this.orignal = orignal;
		this.flag = flag;
		this.webcontentimageses = webcontentimageses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "record_id", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	public Contenttype getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(Contenttype contenttype) {
		this.contenttype = contenttype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "content_type_id")
	public Webcontenttype getWebcontenttype() {
		return this.webcontenttype;
	}

	public void setWebcontenttype(Webcontenttype webcontenttype) {
		this.webcontenttype = webcontenttype;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "tag", nullable = false, length = 45)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "url", nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_date", nullable = false, length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "orignal")
	public String getOrignal() {
		return this.orignal;
	}

	public void setOrignal(String orignal) {
		this.orignal = orignal;
	}

	@Column(name = "flag", nullable = false)
	public Boolean getFlag() {
		return this.flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "weburlrecord")
	public Set<Webcontentimages> getWebcontentimageses() {
		if(this.webcontentimageses==null)
		{
			this.webcontentimageses = new HashSet();
		}
		return this.webcontentimageses;
	}

	public void setWebcontentimageses(Set<Webcontentimages> webcontentimageses) {
		this.webcontentimageses = webcontentimageses;
	}

}