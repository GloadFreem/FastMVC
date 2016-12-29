package com.jinzht.web.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OrderBy;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.message.Enity.Chatroom;

/**
 * BusinessSchool entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_school", catalog = "jinzht2016")
@JsonIgnoreProperties(value = { "businessInvitationCodes","busniessJoins" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BusinessSchool implements java.io.Serializable {

	// Fields

	private Integer bid;
	private BusinessContentType businessContentType;
	private BusinessWeichat businessWeichat;
	private BusniessTag busniessTag;
	private BusinessType businessType;
	private Chatroom chatroom;
	private String bname;
	private Date bstarTime;
	private String bimage;
	private String bdesc;
	private Speechmarker speechmarker;
	private String speechDesc;
	private Integer blimit;
	private String bpriceBase;
	private String bpriceNow;
	private String bextr; // 参课人数
	private String bextr2; // 用户是否已参课
	private Set<BusinessVideo> businessVideos = new HashSet<BusinessVideo>(0);
	private Set<BusniessJoin> busniessJoins = new HashSet<BusniessJoin>(0);
	private Set<BusinessInvitationCode> businessInvitationCodes = new HashSet<BusinessInvitationCode>(
			0);

	// Constructors

	/** default constructor */
	public BusinessSchool() {
	}

	/** full constructor */
	public BusinessSchool(BusinessContentType businessContentType,
			BusinessWeichat businessWeichat, BusniessTag busniessTag,
			BusinessType businessType, Chatroom chatroom, String bname,
			Date bstarTime, String bimage, String bdesc,
			Speechmarker speechMarker, Integer blimit, String bpriceBase,
			String bpriceNow, String bextr, String bextr2,
			Set<BusinessVideo> businessVideos, Set<BusniessJoin> busniessJoins,
			Set<BusinessInvitationCode> businessInvitationCodes,
			String speechDesc) {
		this.businessContentType = businessContentType;
		this.businessWeichat = businessWeichat;
		this.busniessTag = busniessTag;
		this.businessType = businessType;
		this.chatroom = chatroom;
		this.bname = bname;
		this.bstarTime = bstarTime;
		this.bimage = bimage;
		this.bdesc = bdesc;
		this.speechmarker = speechMarker;
		this.blimit = blimit;
		this.bpriceBase = bpriceBase;
		this.bpriceNow = bpriceNow;
		this.bextr = bextr;
		this.bextr2 = bextr2;
		this.businessVideos = businessVideos;
		this.busniessJoins = busniessJoins;
		this.businessInvitationCodes = businessInvitationCodes;
		this.speechDesc = speechDesc;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bid", unique = true, nullable = false)
	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bcontent_type")
	public BusinessContentType getBusinessContentType() {
		return this.businessContentType;
	}

	public void setBusinessContentType(BusinessContentType businessContentType) {
		this.businessContentType = businessContentType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bwechat_id")
	public BusinessWeichat getBusinessWeichat() {
		return this.businessWeichat;
	}

	public void setBusinessWeichat(BusinessWeichat businessWeichat) {
		this.businessWeichat = businessWeichat;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "btag")
	public BusniessTag getBusniessTag() {
		return this.busniessTag;
	}

	public void setBusniessTag(BusniessTag busniessTag) {
		this.busniessTag = busniessTag;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "btype")
	public BusinessType getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bchat_room_id")
	public Chatroom getChatroom() {
		return this.chatroom;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	@Column(name = "bname")
	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Column(name = "bstar_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getBstarTime() {
		return this.bstarTime;
	}

	public void setBstarTime(Date bstarTime) {
		this.bstarTime = bstarTime;
	}

	@Column(name = "bimage")
	public String getBimage() {
		return this.bimage;
	}

	public void setBimage(String bimage) {
		this.bimage = bimage;
	}

	@Column(name = "bdesc")
	public String getBdesc() {
		return this.bdesc;
	}

	public void setBdesc(String bdesc) {
		this.bdesc = bdesc;
	}

	@Column(name = "blimit")
	public Integer getBlimit() {
		return this.blimit;
	}

	public void setBlimit(Integer blimit) {
		this.blimit = blimit;
	}

	@Column(name = "bprice_base")
	public String getBpriceBase() {
		return this.bpriceBase;
	}

	public void setBpriceBase(String bpriceBase) {
		this.bpriceBase = bpriceBase;
	}

	@Column(name = "bprice_now")
	public String getBpriceNow() {
		return this.bpriceNow;
	}

	public void setBpriceNow(String bpriceNow) {
		this.bpriceNow = bpriceNow;
	}

	@Column(name = "bextr")
	public String getBextr() {
		return this.bextr;
	}

	public void setBextr(String bextr) {
		this.bextr = bextr;
	}

	@Column(name = "bextr2", length = 45)
	public String getBextr2() {
		return this.bextr2;
	}

	public void setBextr2(String bextr2) {
		this.bextr2 = bextr2;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "businessSchool")
	@OrderBy(value="vid asc")
	public Set<BusinessVideo> getBusinessVideos() {
		return this.businessVideos;
	}

	public void setBusinessVideos(Set<BusinessVideo> businessVideos) {
		this.businessVideos = businessVideos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "businessSchool")
	public Set<BusniessJoin> getBusniessJoins() {
		return this.busniessJoins;
	}

	public void setBusniessJoins(Set<BusniessJoin> busniessJoins) {
		this.busniessJoins = busniessJoins;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessSchool")
	public Set<BusinessInvitationCode> getBusinessInvitationCodes() {
		return this.businessInvitationCodes;
	}

	public void setBusinessInvitationCodes(
			Set<BusinessInvitationCode> businessInvitationCodes) {
		this.businessInvitationCodes = businessInvitationCodes;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "speek_marker")
	public Speechmarker getSpeechmarker() {
		return this.speechmarker;
	}

	public void setSpeechmarker(Speechmarker speechmarker) {
		this.speechmarker = speechmarker;
	}

	@Column(name = "speech_desc")
	public String getSpeechDesc() {
		return speechDesc;
	}

	public void setSpeechDesc(String speechDesc) {
		this.speechDesc = speechDesc;
	}

}