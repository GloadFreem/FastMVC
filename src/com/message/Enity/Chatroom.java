package com.message.Enity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Chatroom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chatroom", catalog = "jinzht2016")
public class Chatroom implements java.io.Serializable {

	// Fields

	private Integer chatroomId;
	private String name;
	private String description;
	private Integer maxusers;
	private Integer affiliationsCount;
	private String owner;
	private Date createDate;
	private String code;
	private String ext;
	private String ownerName;

	// Constructors

	/** default constructor */
	public Chatroom() {
	}

	/** full constructor */
	public Chatroom(String name, String description, Integer maxusers,
			Integer affiliationsCount, String owner, Date createDate,
			String code, String ext, Integer typeId) {
		this.name = name;
		this.description = description;
		this.maxusers = maxusers;
		this.affiliationsCount = affiliationsCount;
		this.owner = owner;
		this.createDate = createDate;
		this.code = code;
		this.ext = ext;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "chatroom_id", unique = true, nullable = false)
	public Integer getChatroomId() {
		return this.chatroomId;
	}

	public void setChatroomId(Integer chatroomId) {
		this.chatroomId = chatroomId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "maxusers")
	public Integer getMaxusers() {
		return this.maxusers;
	}

	public void setMaxusers(Integer maxusers) {
		this.maxusers = maxusers;
	}

	@Column(name = "affiliations_count")
	public Integer getAffiliationsCount() {
		return this.affiliationsCount;
	}

	public void setAffiliationsCount(Integer affiliationsCount) {
		this.affiliationsCount = affiliationsCount;
	}

	@Column(name = "owner")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ext", length = 45)
	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the ownerName
	 */
	@Column(name="owner_name")
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}