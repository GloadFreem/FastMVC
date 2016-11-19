package com.message.Enity;

import java.util.Date;
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

import com.jinzht.web.entity.Webcontenttype;

/**
 * Msg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg", catalog = "jinzht2016")
public class Msg implements java.io.Serializable {

	// Fields

	private Integer infoId;
	private Webcontenttype webcontenttype;
	private String title;
	private String image;
	private String oringl;
	private Date publicDate;
	private String tab;
	private Integer hot;
	private Set<MsgDetail> msgDetails = new HashSet<MsgDetail>(0);
	private Set<MsgImages> msgImageses = new HashSet<MsgImages>(0);

	// Constructors

	/** default constructor */
	public Msg() {
	}

	/** full constructor */
	public Msg(Webcontenttype webcontenttype, String title, String image,
			String oringl, Date publicDate, String tab, Integer hot,
			Set<MsgDetail> msgDetails, Set<MsgImages> msgImageses) {
		this.webcontenttype = webcontenttype;
		this.title = title;
		this.image = image;
		this.oringl = oringl;
		this.publicDate = publicDate;
		this.tab = tab;
		this.hot = hot;
		this.msgDetails = msgDetails;
		this.msgImageses = msgImageses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "info_id", unique = true, nullable = false)
	public Integer getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	public Webcontenttype getWebcontenttype() {
		return this.webcontenttype;
	}

	public void setWebcontenttype(Webcontenttype webcontenttype) {
		this.webcontenttype = webcontenttype;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "oringl")
	public String getOringl() {
		return this.oringl;
	}

	public void setOringl(String oringl) {
		this.oringl = oringl;
	}

	@Column(name = "public_date", length = 19)
	public Date getPublicDate() {
		return this.publicDate;
	}

	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	@Column(name = "tab")
	public String getTab() {
		return this.tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	@Column(name = "hot")
	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "msg")
	public Set<MsgDetail> getMsgDetails() {
		return this.msgDetails;
	}

	public void setMsgDetails(Set<MsgDetail> msgDetails) {
		this.msgDetails = msgDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "msg")
	public Set<MsgImages> getMsgImageses() {
		return this.msgImageses;
	}

	public void setMsgImageses(Set<MsgImages> msgImageses) {
		this.msgImageses = msgImageses;
	}

}