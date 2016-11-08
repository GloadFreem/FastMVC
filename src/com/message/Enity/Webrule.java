package com.message.Enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Webrule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "webrule", catalog = "jinzht2016")
public class Webrule implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String url;
	private String type;
	private String detailUrl;
	private String PContent;
	private String PContentDel;
	private String PDocid;
	private String PTitle;
	private String PImg;
	private String PTime;
	private String PSource;
	private String PDetailType;
	private String PDetailContent;
	private String PDetailDel;
	private String PDetailImgReplace;
	private String PDetailId;
	private String PDetailContentDel;

	// Constructors

	/** default constructor */
	public Webrule() {
	}

	/** minimal constructor */
	public Webrule(String name) {
		this.name = name;
	}

	/** full constructor */
	public Webrule(String name, String url, String type, String detailUrl,
			String PContent, String PContentDel, String PDocid, String PTitle,
			String PImg, String PTime, String PSource, String PDetailType,
			String PDetailContent, String PDetailDel, String PDetailImgReplace,
			String PDetailId, String PDetailContentDel) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.detailUrl = detailUrl;
		this.PContent = PContent;
		this.PContentDel = PContentDel;
		this.PDocid = PDocid;
		this.PTitle = PTitle;
		this.PImg = PImg;
		this.PTime = PTime;
		this.PSource = PSource;
		this.PDetailType = PDetailType;
		this.PDetailContent = PDetailContent;
		this.PDetailDel = PDetailDel;
		this.PDetailImgReplace = PDetailImgReplace;
		this.PDetailId = PDetailId;
		this.PDetailContentDel = PDetailContentDel;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "detail_url")
	public String getDetailUrl() {
		return this.detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	@Column(name = "p_content", length = 45)
	public String getPContent() {
		return this.PContent;
	}

	public void setPContent(String PContent) {
		this.PContent = PContent;
	}

	@Column(name = "p_content_del", length = 45)
	public String getPContentDel() {
		return this.PContentDel;
	}

	public void setPContentDel(String PContentDel) {
		this.PContentDel = PContentDel;
	}

	@Column(name = "p_docid", length = 45)
	public String getPDocid() {
		return this.PDocid;
	}

	public void setPDocid(String PDocid) {
		this.PDocid = PDocid;
	}

	@Column(name = "p_title", length = 45)
	public String getPTitle() {
		return this.PTitle;
	}

	public void setPTitle(String PTitle) {
		this.PTitle = PTitle;
	}

	@Column(name = "p_img", length = 45)
	public String getPImg() {
		return this.PImg;
	}

	public void setPImg(String PImg) {
		this.PImg = PImg;
	}

	@Column(name = "p_time", length = 45)
	public String getPTime() {
		return this.PTime;
	}

	public void setPTime(String PTime) {
		this.PTime = PTime;
	}

	@Column(name = "p_source")
	public String getPSource() {
		return this.PSource;
	}

	public void setPSource(String PSource) {
		this.PSource = PSource;
	}

	@Column(name = "p_detail_type", length = 45)
	public String getPDetailType() {
		return this.PDetailType;
	}

	public void setPDetailType(String PDetailType) {
		this.PDetailType = PDetailType;
	}

	@Column(name = "p_detail_content", length = 45)
	public String getPDetailContent() {
		return this.PDetailContent;
	}

	public void setPDetailContent(String PDetailContent) {
		this.PDetailContent = PDetailContent;
	}

	@Column(name = "p_detail_del", length = 45)
	public String getPDetailDel() {
		return this.PDetailDel;
	}

	public void setPDetailDel(String PDetailDel) {
		this.PDetailDel = PDetailDel;
	}

	@Column(name = "p_detail_img_replace", length = 45)
	public String getPDetailImgReplace() {
		return this.PDetailImgReplace;
	}

	public void setPDetailImgReplace(String PDetailImgReplace) {
		this.PDetailImgReplace = PDetailImgReplace;
	}

	@Column(name = "p_detail_id")
	public String getPDetailId() {
		return this.PDetailId;
	}

	public void setPDetailId(String PDetailId) {
		this.PDetailId = PDetailId;
	}

	@Column(name = "p_detail_content_del", length = 45)
	public String getPDetailContentDel() {
		return this.PDetailContentDel;
	}

	public void setPDetailContentDel(String PDetailContentDel) {
		this.PDetailContentDel = PDetailContentDel;
	}

}