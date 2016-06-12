package com.jinzht.web.entity;

import java.sql.Timestamp;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.mapping.List;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Action entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "action", catalog = "jinzht2016")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"actionshares","attentions","actioncomments","actionshares","initiateUser"})

public class Action implements java.io.Serializable {

	// Fields

	private Integer actionId;
	private String name;
	private String address;
	private String description;
	private String initiateUser;
	private Short memberLimit;
	private Date startTime;
	private Date endTime;
	private Set<Actionprise> actionprises = new HashSet<Actionprise>(0);
	private Set<Attention> attentions = new HashSet<Attention>(0);
	private Set<Actioncomment> actioncomments = new HashSet<Actioncomment>(0);
	private Set<Actionshare> actionshares = new HashSet<Actionshare>(0);
	private Set<Actionimages> actionimages = new HashSet<Actionimages>(0);
	private short type;
	private boolean flag; //是否参加报名
	private boolean attended;

	// Constructors

	/** default constructor */
	public Action() {
	}

	/** full constructor */
	public Action(String name, String address, String description,
			String initiateUser, Short memberLimit, Timestamp startTime,
			Timestamp endTime, Set<Actionprise> actionprises,
			Set<Attention> attentions, Set<Actioncomment> actioncomments,
			Set<Actionshare> actionshares) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.initiateUser = initiateUser;
		this.memberLimit = memberLimit;
		this.startTime = startTime;
		this.endTime = endTime;
		this.actionprises = actionprises;
		this.attentions = attentions;
		this.actioncomments = actioncomments;
		this.actionshares = actionshares;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "action_id", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "initiate_user", length = 256)
	public String getInitiateUser() {
		return this.initiateUser;
	}

	public void setInitiateUser(String initiateUser) {
		this.initiateUser = initiateUser;
	}

	@Column(name = "member_limit")
	public Short getMemberLimit() {
		return this.memberLimit;
	}

	public void setMemberLimit(Short memberLimit) {
		this.memberLimit = memberLimit;
	}

	@Column(name = "start_time", length = 0)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 0)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "action")
	@OrderBy(value = "priseId desc")
	public Set<Actionprise> getActionprises() {
		return this.actionprises;
	}

	public void setActionprises(Set<Actionprise> actionprises) {
		this.actionprises = actionprises;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "action")
	public Set<Attention> getAttentions() {
		return this.attentions;
	}

	public void setAttentions(Set<Attention> attentions) {
		this.attentions = attentions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "action")
	@OrderBy(value="commentId desc")
	public Set<Actioncomment> getActioncomments() {
		return this.actioncomments;
	}

	public void setActioncomments(Set<Actioncomment> actioncomments) {
		this.actioncomments = actioncomments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "action")
	public Set<Actionshare> getActionshares() {
		return this.actionshares;
	}

	public void setActionshares(Set<Actionshare> actionshares) {
		this.actionshares = actionshares;
	}

	@Column(name = "type", length = 0)
	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "action")
	public Set<Actionimages> getActionimages() {
		return actionimages;
	}

	public void setActionimages(Set<Actionimages> actionimages) {
		this.actionimages = actionimages;
	}

	@Column(name = "flag", length = 1)
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isAttended() {
		
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}




}