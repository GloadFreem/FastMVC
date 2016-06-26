package com.jinzht.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Attention entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attention", catalog = "jinzht2016")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"action","users"})
public class Attention implements java.io.Serializable {

	// Fields

	private Integer attendUid;
	private String  userName;
	private String headerPicture;
	private Users users;
	private Action action;
	private String content;
	private Date enrollDate;

	// Constructors

	/** default constructor */
	public Attention() {
	}

	/** full constructor */
	public Attention(Users users, Action action, String content,
			Date enrollDate) {
		this.users = users;
		this.action = action;
		this.content = content;
		this.enrollDate = enrollDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "attend_uid", unique = true, nullable = false)
	public Integer getAttendUid() {
		return this.attendUid;
	}

	public void setAttendUid(Integer attendUid) {
		this.attendUid = attendUid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_id")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Column(name = "content", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "enroll_date", length = 0)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	public Date getEnrollDate() {
		return this.enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeaderPicture() {
		return headerPicture;
	}

	public void setHeaderPicture(String headerPicture) {
		this.headerPicture = headerPicture;
	}

}